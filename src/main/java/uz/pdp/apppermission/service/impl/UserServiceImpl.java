package uz.pdp.apppermission.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.apppermission.domain.User;
import uz.pdp.apppermission.exceptions.ResourceNotFoundException;
import uz.pdp.apppermission.mapper.MapstructMapper;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.UserDto;
import uz.pdp.apppermission.repository.RoleRepo;
import uz.pdp.apppermission.repository.UserRepo;
import uz.pdp.apppermission.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepo roleRepo;

    private final MapstructMapper mapper;

    @Override
    public ApiResponse get(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("user", "id", id);
        }
        return new ApiResponse("OK", true,mapper.toUserDto(optionalUser.get()));
    }

    @Override
    public ApiResponse get() {
        return new ApiResponse("OK", true, mapper.toUserDto(userRepo.findAll()));
    }

    @Override
    public ApiResponse add(UserDto dto) {
        if (userRepo.existsByUsername(dto.getUsername()))
            return new ApiResponse("User has already existed", false);
        User user = new User(
                dto.getFullName(),
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                roleRepo.findById(dto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("role", "id", dto.getRoleId())),
                true
        );
        userRepo.save(user);
        return new ApiResponse("Created", true);
    }

    @Override
    public ApiResponse edit(Long id, UserDto dto) {
        if (userRepo.existsByUsernameAndIdNot(dto.getUsername(), id))
            return new ApiResponse("Username has already used", false);
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent())
            throw new ResourceNotFoundException("user", "id", id);
        User user = optionalUser.get();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setRole(roleRepo.findById(dto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("role", "id", dto.getRoleId())));
        userRepo.save(user);
        return new ApiResponse("Updated", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        if (!userRepo.existsById(id))
            throw new ResourceNotFoundException("user", "id", id);
        userRepo.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
