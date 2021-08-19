package uz.pdp.apppermission.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.apppermission.domain.Post;
import uz.pdp.apppermission.exceptions.ResourceNotFoundException;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.PostDto;
import uz.pdp.apppermission.repository.PostRepo;
import uz.pdp.apppermission.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;

    @Override
    public ApiResponse get() {
        return new ApiResponse("OK", true, postRepo.findAll());
    }

    @Override
    public ApiResponse get(Long id) {
        return new ApiResponse("OK", true, postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id)));
    }

    @Override
    public ApiResponse add(PostDto dto, HttpServletRequest request) {
        Post post = new Post();
        post.setText(dto.getText());
        post.setTitle(dto.getTitle());
        post.setUrl(request.getRequestURL().toString() + "/" + dto.getTitle().replace(" ", "-"));
        postRepo.save(post);
        return new ApiResponse("Created", true);
    }

    @Override
    public ApiResponse edit(Long id, PostDto dto) {
        Optional<Post> optionalPost = postRepo.findById(id);
        if (!optionalPost.isPresent())
            throw new ResourceNotFoundException("post", "id", id);
        Post post = optionalPost.get();
        String oldUrl = post.getUrl();
        post.setText(dto.getText());
        post.setTitle(dto.getTitle());
        post.setUrl(oldUrl.substring(0, oldUrl.lastIndexOf("/")) + dto.getTitle().replace(" ", "-"));
        postRepo.save(post);
        return new ApiResponse("Edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        if (!postRepo.existsById(id))
            throw new ResourceNotFoundException("post", "id", id);
        postRepo.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
