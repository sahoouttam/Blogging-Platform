package com.blog.platform.controller;

import com.blog.platform.payload.request.BoardRequest;
import com.blog.platform.payload.response.BoardResponse;
import com.blog.platform.payload.response.PinResponse;
import com.blog.platform.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<HttpStatus> createBoard(@RequestBody BoardRequest boardRequest) {
        return boardService.createBoard(boardRequest);
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<HttpStatus> deleteBoardById(@PathVariable Long id) {
        return boardService.deleteBoardById(id);
    }

    @GetMapping("/boards/{id}/pins")
    public ResponseEntity<List<PinResponse>> getAllPinsByBoardId(@PathVariable Long id) {
        return boardService.getAllPinsByBoardId(id);
    }
}
