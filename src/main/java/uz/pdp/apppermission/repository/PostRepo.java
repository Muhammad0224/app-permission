package uz.pdp.apppermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.apppermission.domain.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
}
