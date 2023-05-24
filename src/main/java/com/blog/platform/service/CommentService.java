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

    @Autowired
    private final CommentRepository commentRepository;

    public ResponseEntity<HttpStatus> createComment(CommentRequest commentRequest) {
        Comment comment = CommentMapper.convertCommentRequest(commentRequest);
        commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Cacheable(value = "comment_by_name")
    public ResponseEntity<CommentResponse> getCommentByName(String name) {
        CommentResponse comment = commentRepository.findByName(name)
                .map(CommentMapper::convertComment)
                .orElseThrow(() -> new CommentNotFoundException(Constants.COMMENT_NOT_FOUND));

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @Cacheable(value = "comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBody(String body) {
        List<CommentResponse> comments = commentRepository.findFirst10ByBodyContaining(body)
                .stream()
                .map(CommentMapper::convertComment)
                .limit(10)
                .collect(Collectors.toList());

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateComment(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id)
                        .orElseThrow(() -> new CommentNotFoundException(Constants.COMMENT_NOT_FOUND));
        CommentMapper.updateComment(comment, commentRequest);
        commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CacheEvict(value = "comments")
    public ResponseEntity<HttpStatus> deleteComment(Long id) {
        commentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
