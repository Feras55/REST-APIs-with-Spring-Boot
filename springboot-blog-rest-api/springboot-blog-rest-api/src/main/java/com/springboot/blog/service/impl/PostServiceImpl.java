package com.springboot.blog.service.impl;

import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service //to declare the class as service class
public class PostServiceImpl implements PostService {

    //inject post repository, constructor-based
    private PostRepository postRepository;

    //@Autowired //from spring 4.3 onwards, if a class is configured as a spring bean, and it has only one constructor,
    // you don't need the @AutoWired annotation to inject dependencies
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO into entity
        Post post = mapDtoToEntity(postDto);

        //save entity
        postRepository.save(post);

        //convert entity to DTO,
        // !!question here: Why not return the DTO passed as a parameter? We need to send the saved object return back to the client.
        //Note that we are adding the post id in the response DTO.
        PostDto postResponeDto = mapEntityToDto(post);

        //return responseDTO
        return postResponeDto;
    }

    private static Post mapDtoToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

    private static PostDto mapEntityToDto(Post post) {
        PostDto postResponeDto = new PostDto();
        postResponeDto.setId(post.getId());
        postResponeDto.setTitle(post.getTitle());
        postResponeDto.setContent(post.getContent());
        postResponeDto.setDescription(post.getDescription());
        return postResponeDto;
    }

    @Override
    public List<PostDto> getAllPosts() {

        List<Post> posts = postRepository.findAll();
        List<PostDto> postsResponseDto = new ArrayList<>();
        //Lamda expression, one way to convert list of datatype into list of another
//        posts.forEach((post -> {
//            postsResponseDto.add(mapEntityToDto(post));
//        }));

        //another appraoch
        postsResponseDto = posts.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());

        return postsResponseDto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapEntityToDto(post);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        //get post
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        //update
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        //save and return
        Post updatedPost = postRepository.save(post);

        return mapEntityToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        //find post
       Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }


}