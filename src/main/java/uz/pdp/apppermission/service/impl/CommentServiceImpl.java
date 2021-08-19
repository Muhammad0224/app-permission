package uz.pdp.apppermission.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.apppermission.domain.Comment;
import uz.pdp.apppermission.domain.Post;
import uz.pdp.apppermission.domain.User;
import uz.pdp.apppermission.exceptions.ForbiddenException;
import uz.pdp.apppermission.exceptions.ResourceNotFoundException;
import uz.pdp.apppermission.model.ApiResponse;
import uz.pdp.apppermission.model.CommentDto;
import uz.pdp.apppermission.model.PostDto;
import uz.pdp.apppermission.repository.CommentRepo;
import uz.pdp.apppermission.repository.PostRepo;
import uz.pdp.apppermission.service.CommentService;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;

    private final PostRepo postRepo;

    @Override
    public ApiResponse get() {
        return new ApiResponse("OK", true, commentRepo.findAll());
    }

    @Override
    public ApiResponse get(Long id) {
        return new ApiResponse("OK", true, commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id)));
    }

    @Override
    public ApiResponse add(CommentDto dto) {
        Optional<Post> optionalPost = postRepo.findById(dto.getPostId());
        if (!optionalPost.isPresent())
            throw new ResourceNotFoundException("post", "id", dto.getPostId());
        Post post = optionalPost.get();
        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setPost(post);
        commentRepo.save(comment);
        return new ApiResponse("Created", true);
    }

    @Override
    public ApiResponse edit(Long id, CommentDto dto) {
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if (!optionalComment.isPresent())
            throw new ResourceNotFoundException("comment", "id", id);
        Comment comment = optionalComment.get();
        if (!User.getCurrentUser().getUsername().equals(comment.getCreatedBy().getUsername()))
            throw new ForbiddenException("Access", "You do not have access to edit this comment");
        Optional<Post> optionalPost = postRepo.findById(dto.getPostId());
        if (!optionalPost.isPresent())
            throw new ResourceNotFoundException("post", "id", dto.getPostId());
        Post post = optionalPost.get();

        comment.setText(dto.getText());
        comment.setPost(post);
        commentRepo.save(comment);
        return new ApiResponse("Edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if (!optionalComment.isPresent())
            throw new ResourceNotFoundException("comment", "id", id);
        Comment comment = optionalComment.get();
        if (!User.getCurrentUser().getUsername().equals(comment.getCreatedBy().getUsername())
            && !User.getCurrentUser()
                .getAuthorities().contains(new SimpleGrantedAuthority("DELETE_COMMENT")))
            throw new ForbiddenException("Access", "You do not have access to edit this comment");
        commentRepo.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
