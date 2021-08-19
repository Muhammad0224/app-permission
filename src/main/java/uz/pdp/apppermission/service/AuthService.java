package uz.pdp.apppermission.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.LoginDto;
import uz.pdp.apppermission.model.RegisterDto;

public interface AuthService{
    ApiResponse register(RegisterDto dto);

    ApiResponse login(LoginDto dto);
}
