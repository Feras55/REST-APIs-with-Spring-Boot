package com.springboot.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //Lombok.data annotation adds setters, getters and toString
@AllArgsConstructor //generates all args constructor
@NoArgsConstructor //generate no args constructor

@Entity
@Table(
        name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})} //states that title is unique
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;
}
