package com.springboot.blog.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
