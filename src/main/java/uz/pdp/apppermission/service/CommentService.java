package uz.pdp.apppermission.service;

import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.CommentDto;

public interface CommentService {
    ApiResponse get();

    ApiResponse get(Long id);

    ApiResponse add(CommentDto dto);

    ApiResponse edit(Long id, CommentDto dto);

    ApiResponse delete(Long id);
}
