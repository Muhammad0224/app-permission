package uz.pdp.apppermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.apppermission.domain.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
}
