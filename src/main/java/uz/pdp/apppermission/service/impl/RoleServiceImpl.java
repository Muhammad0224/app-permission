package uz.pdp.apppermission.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.apppermission.domain.Role;
import uz.pdp.apppermission.exceptions.ResourceNotFoundException;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.RoleDto;
import uz.pdp.apppermission.model.UserDto;
import uz.pdp.apppermission.repository.RoleRepo;
import uz.pdp.apppermission.service.RoleService;
import uz.pdp.apppermission.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public ApiResponse add(RoleDto dto) {
        if (roleRepo.existsByName(dto.getName()))
            return new ApiResponse("Role has already existed", false);

        Role role = new Role(
                dto.getName(),
                dto.getPermissions(),
                dto.getDescription()
        );
        roleRepo.save(role);

        return new ApiResponse("OK", true);
    }

    @Override
    public ApiResponse edit(Long id, RoleDto dto) {
        if (roleRepo.existsByNameAndIdNot(dto.getName(),id))
            return new ApiResponse("Role has already existed", false);

        Optional<Role> optionalRole = roleRepo.findById(id);
        if (!optionalRole.isPresent())
            throw new ResourceNotFoundException("role", "id", id);
        Role role = optionalRole.get();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setPermissions(dto.getPermissions());
        roleRepo.save(role);
        return new ApiResponse("Updated", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        if (!roleRepo.existsById(id))
            throw new ResourceNotFoundException("role", "id", id);
        roleRepo.deleteById(id);
        return null;
    }

    @Override
    public ApiResponse get(Long id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        return new ApiResponse("OK", true, optionalRole.orElseThrow(() -> new ResourceNotFoundException("role", "id", id)));
    }

    @Override
    public ApiResponse get() {
        return new ApiResponse("OK", true, roleRepo.findAll());
    }
}
