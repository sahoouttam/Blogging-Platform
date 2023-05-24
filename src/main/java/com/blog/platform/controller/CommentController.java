package com.blog.platform.controller;

import com.blog.platform.payload.request.CommentRequest;
import com.blog.platform.payload.response.CommentResponse;
import com.blog.platform.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<HttpStatus> createComment(@RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentRequest);
    }

    @GetMapping("/comments/{name}")
    public ResponseEntity<CommentResponse> getCommentByName(@PathVariable String name) {
        return commentService.getCommentByName(name);
    }

    @GetMapping("/comments/{body}")
    public ResponseEntity<List<CommentResponse>> getCommentsByBody(@PathVariable String body) {
        return commentService.getCommentsByBody(body);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> updateComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(id, commentRequest);
    }

    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
