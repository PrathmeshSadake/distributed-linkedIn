package me.prathmeshsadake.linkedin.postService.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.prathmeshsadake.linkedin.postService.entity.Post;
import me.prathmeshsadake.linkedin.postService.entity.PostLike;
import me.prathmeshsadake.linkedin.postService.exception.BadRequestException;
import me.prathmeshsadake.linkedin.postService.exception.ResourceNotFoundException;
import me.prathmeshsadake.linkedin.postService.repository.PostLikeRepository;
import me.prathmeshsadake.linkedin.postService.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likePost(Long postId) {
        Long userId = 2L;
        log.info("Liking post like id {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found"));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(hasAlreadyLiked){
            throw new BadRequestException("Post is already liked");
        }
        PostLike postLike = new PostLike();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
        postLikeRepository.save(postLike);
        // TODO: sends notification to owner of the post

    }

    @Transactional
    public void unlikePost(Long postId){
        Long userId = 2L;
        log.info("Liking post like id {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found"));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(!hasAlreadyLiked){
            throw new BadRequestException("You cannot unlike the post you have not liked");
        }

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);


    }

}
