package me.prathmeshsadake.linkedin.postService.repository;

import lombok.Data;
import me.prathmeshsadake.linkedin.postService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
