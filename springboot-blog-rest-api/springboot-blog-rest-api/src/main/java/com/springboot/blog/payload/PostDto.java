package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

//the dto attributes should match the model object attributes, refer to model.Post
@Data //to internally generate setters,getters, etc.
public class PostDto {
    private long id;

    //title should not be empty or null
    //title should be at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    //Description should not be empty or null
    //Description should be at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post Description should be at least 10 characters")
    private String description;

    //Description should not be empty or null
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

}
