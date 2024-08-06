package com.Week4HW.Week4.services;

import com.Week4HW.Week4.dto.PostDTO;
import com.Week4HW.Week4.entities.PostEntity;
import com.Week4HW.Week4.exceptions.ResourceNotFoundException;
import com.Week4HW.Week4.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> mapper.map(postEntity, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = mapper.map(inputPost, PostEntity.class);
        return mapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found with id :" + postId));
        return mapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id :" + postId));
        inputPost.setId(postId);
        mapper.map(olderPost, inputPost);
        PostEntity savedPost = postRepository.save(olderPost);
        return mapper.map(savedPost, PostDTO.class);
    }
}
