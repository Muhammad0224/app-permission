package uz.pdp.apppermission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppermission.aop.CheckPermission;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.CommentDto;
import uz.pdp.apppermission.service.CommentService;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> get() {
        ApiResponse apiResponse = commentService.get();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.get(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(permission = "ADD_COMMENT")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CommentDto dto) {
        ApiResponse apiResponse = commentService.add(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(permission = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CommentDto dto) {
        ApiResponse apiResponse = commentService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_COMMENT', 'DELETE_MY_COMMENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
