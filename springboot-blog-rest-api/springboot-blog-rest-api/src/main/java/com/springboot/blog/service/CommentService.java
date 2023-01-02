package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    List<CommentDto> getAllComments(long postId, int pageNo, int pageSize, String sortBy, String sortDir);
    CommentDto getCommentById(long id);
    CommentDto updateComment(long id, CommentDto commentDto);

    void deleteComment(long id);
}
