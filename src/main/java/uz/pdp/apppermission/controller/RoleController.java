package uz.pdp.apppermission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppermission.aop.CheckPermission;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.RoleDto;
import uz.pdp.apppermission.model.UserDto;
import uz.pdp.apppermission.service.RoleService;
import uz.pdp.apppermission.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @CheckPermission(permission = "VIEW_ROLES")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        ApiResponse apiResponse = roleService.get(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(permission = "VIEW_ROLES")
    @GetMapping
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = roleService.get();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody RoleDto dto) {
        ApiResponse apiResponse = roleService.add(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @CheckPermission(permission = "EDIT_ROLE")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody RoleDto dto) {
        ApiResponse apiResponse = roleService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(permission = "DELETE_ROLE")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = roleService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

}
