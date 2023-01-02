package com.springboot.blog.service.impl;

import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponseDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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
        Post post = mapToEntity(postDto);

        //save entity
        postRepository.save(post);

        //convert entity to DTO,
        // !!question here: Why not return the DTO passed as a parameter? We need to send the saved object return back to the client.
        //Note that we are adding the post id in the response DTO.
        PostDto postResponeDto = mapToDto(post);

        //return responseDTO
        return postResponeDto;
    }

    private static Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

    private static PostDto mapToDto(Post post) {
        PostDto postResponeDto = new PostDto();
        postResponeDto.setId(post.getId());
        postResponeDto.setTitle(post.getTitle());
        postResponeDto.setContent(post.getContent());
        postResponeDto.setDescription(post.getDescription());
        return postResponeDto;
    }

    @Override
    public PostResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        //Add pagination and sorting

        //Create Pageable instance
        //Create instance of sort according to sort direction
        Sort mySort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, mySort);

        Page<Post> posts = postRepository.findAll(pageable);

        //get content for page objects
        List<Post> listOfPosts = posts.getContent();


        //Lamda expression, one way to convert list of datatype into list of another
//        posts.forEach((post -> {
//            postsResponseDto.add(mapEntityToDto(post));
//        }));

        //another appraoch
        List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponseDto postResponseDto = new PostResponseDto(content, posts.getNumber(), posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());

        return postResponseDto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
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

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        //find post
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }


}
