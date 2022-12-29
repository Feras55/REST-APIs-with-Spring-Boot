package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(long id, PostDto postDto);

    void deletePost(long id);
}
