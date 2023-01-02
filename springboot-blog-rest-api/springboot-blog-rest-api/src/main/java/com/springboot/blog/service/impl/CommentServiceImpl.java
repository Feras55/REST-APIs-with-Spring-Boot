package com.springboot.blog.service.impl;

import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.*;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {

        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post", "ID", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        CommentDto commentResponseDto = mapToDto(newComment);

        return commentResponseDto;
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId, int pageNo, int pageSize, String sortBy, String sortDir) {
        //retrieve comments by post id
        List<Comment>comments = commentRepository.getCommentsByPostId(postId);

        List<CommentDto>commentsResponse = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return commentsResponse;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //retrieve post
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post", "ID" , postId));

        //retrieve comment by id

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment", "ID", commentId)
        );

        //check if comment is related to the particular post (i.e the comment is on the post with the specified postId)
        if(!comment.getPost().equals(post)){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto) {
        return null;
    }

    @Override
    public void deleteComment(long id) {

    }

    private static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        return comment;
    }

    private static CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());

        return commentDto;
    }

    private CommentRepository commentRepository;
    private PostRepository postRepository;
}
