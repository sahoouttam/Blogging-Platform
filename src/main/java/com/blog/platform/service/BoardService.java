package com.blog.platform.service;

import com.blog.platform.entity.Board;
import com.blog.platform.exception.BoardNotFoundException;
import com.blog.platform.mapper.BoardMapper;
import com.blog.platform.mapper.PinMapper;
import com.blog.platform.payload.request.BoardRequest;
import com.blog.platform.payload.response.BoardResponse;
import com.blog.platform.payload.response.PinResponse;
import com.blog.platform.repository.BoardRepository;
import com.blog.platform.repository.UserRepository;
import com.blog.platform.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    public ResponseEntity<HttpStatus> createBoard(BoardRequest boardRequest) {
        Board board = BoardMapper.convertBoardRequest(boardRequest);
        boardRepository.save(board);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<BoardResponse> getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(Constants.BOARD_NOT_FOUND));
        return new ResponseEntity<>(BoardMapper.convertBoard(board), HttpStatus.OK);
    }

    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<BoardResponse> boards = boardRepository.findAll()
                .stream()
                .map(BoardMapper::convertBoard)
                .collect(Collectors.toList());

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteBoardById(Long id) {
        boardRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<PinResponse>> getAllPinsByBoardId(Long id) {
        List<PinResponse> pins = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(Constants.BOARD_NOT_FOUND))
                .getPins()
                .stream()
                .map(PinMapper::convertPin)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pins, HttpStatus.OK);
    }
}
