package com.springboot.blog.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

//the dto attributes should match the model object attributes, refer to model.Post
@Data //to internally generate setters,getters, etc.
@Getter
@Setter
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;

}
