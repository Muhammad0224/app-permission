package uz.pdp.apppermission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.RegisterDto;
import uz.pdp.apppermission.model.UserDto;
import uz.pdp.apppermission.service.AuthService;
import uz.pdp.apppermission.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('VIEW_USERS')")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        ApiResponse apiResponse = userService.get(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('VIEW_USERS')")
    @GetMapping
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = userService.get();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADD_USER')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody UserDto dto) {
        ApiResponse apiResponse = userService.add(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        ApiResponse apiResponse = userService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

}
