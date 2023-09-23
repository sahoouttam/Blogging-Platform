package com.blog.platform.controller;

import com.blog.platform.entity.Comment;
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
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.createComment(commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/comments/{name}")
    public ResponseEntity<CommentResponse> getCommentByName(@PathVariable String name) {
        CommentResponse commentResponse = commentService.getCommentByName(name);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @GetMapping("/comments/{body}")
    public ResponseEntity<List<CommentResponse>> getCommentsByBody(@PathVariable String body) {
        List<CommentResponse> commentResponses = commentService.getCommentsByBody(body);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.updateComment(id, commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
