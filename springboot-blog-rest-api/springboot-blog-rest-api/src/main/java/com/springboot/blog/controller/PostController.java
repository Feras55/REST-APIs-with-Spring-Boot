package com.springboot.blog.controller;

import com.springboot.blog.model.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    //Controller should have instance of the service
    PostService postService;
    //constructor

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create New Post
    @PostMapping
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){

        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable(name = "id") long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePostById(@PathVariable(name = "id") long postId, @RequestBody PostDto postDto){
        return  ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

}
