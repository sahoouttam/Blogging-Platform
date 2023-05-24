package com.blog.platform.mapper;

import com.blog.platform.entity.Board;
import com.blog.platform.payload.request.BoardRequest;
import com.blog.platform.payload.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BoardMapper {

    public static Board convertBoardRequest(BoardRequest boardRequest) {
        return Board.builder()
                .title(boardRequest.getTitle())
                .user(boardRequest.getUser())
                .pins(boardRequest.getPins())
                .createdOn(LocalDate.now())
                .updatedOn(LocalDate.now())
                .build();
    }

    public static BoardResponse convertBoard(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .user(board.getUser())
                .pins(board.getPins())
                .build();
    }
}
