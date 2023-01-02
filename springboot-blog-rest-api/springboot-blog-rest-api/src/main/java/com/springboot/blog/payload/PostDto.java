package com.springboot.blog.payload;

import com.springboot.blog.model.Comment;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

//the dto attributes should match the model object attributes, refer to model.Post
@Data //to internally generate setters,getters, etc.
public class PostDto {

    private long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments = new HashSet<>();

}
