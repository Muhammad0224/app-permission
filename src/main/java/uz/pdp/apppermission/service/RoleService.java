package uz.pdp.apppermission.service;

import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.RoleDto;

public interface RoleService {
    ApiResponse add(RoleDto dto);

    ApiResponse edit(Long id, RoleDto dto);

    ApiResponse delete(Long id);

    ApiResponse get(Long id);

    ApiResponse get();
}
