package com.blog.platform.service;

import com.blog.platform.entity.Comment;
import com.blog.platform.exception.CommentNotFoundException;
import com.blog.platform.mapper.CommentMapper;
import com.blog.platform.payload.request.CommentRequest;
import com.blog.platform.payload.response.CommentResponse;
import com.blog.platform.repository.CommentRepository;
import com.blog.platform.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public Comment createComment(CommentRequest commentRequest) {
        Comment comment = commentMapper.convertCommentRequest(commentRequest);
        return commentRepository.save(comment);
    }

    @Cacheable(value = "comment_by_name")
    public CommentResponse getCommentByName(String name) {
        return commentRepository.findByName(name)
                .map(commentMapper::convertComment)
                .orElseThrow(() -> new CommentNotFoundException(Constants.COMMENT_NOT_FOUND));
    }

    @Cacheable(value = "comments")
    public List<CommentResponse> getCommentsByBody(String body) {
        return commentRepository.findFirst10ByBodyContaining(body)
                .stream()
                .map(commentMapper::convertComment)
                .limit(10)
                .collect(Collectors.toList());
    }

    public Comment updateComment(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id)
                        .orElseThrow(() -> new CommentNotFoundException(Constants.COMMENT_NOT_FOUND));
        CommentMapper.updateComment(comment, commentRequest);
        return commentRepository.save(comment);
    }

    @CacheEvict(value = "comments")
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
