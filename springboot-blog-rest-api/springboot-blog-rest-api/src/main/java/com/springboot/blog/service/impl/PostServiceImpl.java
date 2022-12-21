package com.springboot.blog.service.impl;

import com.springboot.blog.model.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //to declare the class as service class
public class PostServiceImpl implements PostService {

    //inject post repository, constructor-based

    private PostRepository postRepository;

    //@Autowired //from spring 4.3 onwards, if a class is configured as a spring bean and it has only one constructor, you don't need the @AutoWired annotation to inject dependencies
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO into entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        //save entity
        postRepository.save(post);

        //convert entity to DTO, !!question here: Why not return the DTO passed as a parameter?
        PostDto postResponeDto = new PostDto();
        postResponeDto.setId(post.getId());
        postResponeDto.setTitle(post.getTitle());
        postResponeDto.setContent(post.getContent());
        postResponeDto.setDescription(post.getDescription());

        //return responseDTO
        return postResponeDto;
    }
}
