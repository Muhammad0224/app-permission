package uz.pdp.apppermission.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.apppermission.domain.User;
import uz.pdp.apppermission.exceptions.ResourceNotFoundException;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.LoginDto;
import uz.pdp.apppermission.model.RegisterDto;
import uz.pdp.apppermission.repository.RoleRepo;
import uz.pdp.apppermission.repository.UserRepo;
import uz.pdp.apppermission.security.JWTProvider;
import uz.pdp.apppermission.service.AuthService;
import uz.pdp.apppermission.utils.AppConstants;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    @Override
    public ApiResponse register(RegisterDto dto) {
        if (!dto.getPassword().equals(dto.getPrePassword()))
            return new ApiResponse("Passwords are not compatible", false);

        if (userRepo.existsByUsername(dto.getUsername()))
            return new ApiResponse("User has already existed", false);

        User user = new User(
                dto.getFullName(),
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                roleRepo.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("role", "name", AppConstants.USER)),
                true
        );

        userRepo.save(user);
        return new ApiResponse("OK", true);
    }

    @Override
    public ApiResponse login(LoginDto dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        User user = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
        return new ApiResponse("OK", true, token);
    }
}
