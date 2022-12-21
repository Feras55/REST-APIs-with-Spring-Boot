package com.springboot.blog.payload;

import lombok.Data;

//the dto attributes should match the model object attributes, refer to model.Post
@Data //to internally generate setters,getters, etc.
public class PostDto {

    private long id;
    private String title;
    private String description;
    private String content;

}
