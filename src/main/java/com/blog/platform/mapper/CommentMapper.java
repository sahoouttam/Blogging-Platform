package com.blog.platform.mapper;

import com.blog.platform.entity.Category;
import com.blog.platform.entity.Comment;
import com.blog.platform.payload.request.CategoryRequest;
import com.blog.platform.payload.request.CommentRequest;
import com.blog.platform.payload.response.CategoryResponse;
import com.blog.platform.payload.response.CommentResponse;

import java.time.LocalDate;

public class CommentMapper {

    public CommentResponse convertComment(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .name(comment.getName())
                .body(comment.getBody())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }

    public Comment convertCommentRequest(CommentRequest commentRequest) {
        return Comment.builder()
                .name(commentRequest.getName())
                .body(commentRequest.getBody())
                .post(commentRequest.getPost())
                .user(commentRequest.getUser())
                .createdOn(LocalDate.now())
                .updatedOn(LocalDate.now())
                .build();
    }

    public static void updateComment(Comment comment, CommentRequest commentRequest) {
        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());
        comment.setPost(commentRequest.getPost());
        comment.setUser(commentRequest.getUser());
        comment.setCreatedOn(comment.getCreatedOn());
        comment.setUpdatedOn(LocalDate.now());
    }
}