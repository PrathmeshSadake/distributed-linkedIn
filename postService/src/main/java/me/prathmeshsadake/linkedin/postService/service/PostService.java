package me.prathmeshsadake.linkedin.postService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.prathmeshsadake.linkedin.postService.dto.PostCreateRequestDto;
import me.prathmeshsadake.linkedin.postService.dto.PostDto;
import me.prathmeshsadake.linkedin.postService.entity.Post;
import me.prathmeshsadake.linkedin.postService.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return new ModelMapper().map(post, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return post.map(value -> new ModelMapper().map(value, PostDto.class)).orElse(null);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<PostDto>
    }
}
