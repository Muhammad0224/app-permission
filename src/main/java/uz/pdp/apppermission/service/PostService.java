package uz.pdp.apppermission.service;

import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.PostDto;

import javax.servlet.http.HttpServletRequest;

public interface PostService {
    ApiResponse get();

    ApiResponse get(Long id);

    ApiResponse add(PostDto dto, HttpServletRequest request);

    ApiResponse edit(Long id, PostDto dto);

    ApiResponse delete(Long id);
}
