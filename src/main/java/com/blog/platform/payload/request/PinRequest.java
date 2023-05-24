package com.blog.platform.payload.request;

import com.blog.platform.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PinRequest {

    private String title;

    private String url;

    private Board board;
}
