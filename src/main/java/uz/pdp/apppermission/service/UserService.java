package uz.pdp.apppermission.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.UserDto;

public interface UserService extends UserDetailsService {
    ApiResponse add(UserDto dto);

    ApiResponse edit(Long id, UserDto dto);

    ApiResponse delete(Long id);

    ApiResponse get(Long id);

    ApiResponse get();
}
