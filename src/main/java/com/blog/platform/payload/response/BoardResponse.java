package com.blog.platform.payload.response;

import com.blog.platform.entity.Pin;
import com.blog.platform.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {

    private Long id;

    private String title;

    private User user;

    private List<Pin> pins;
}
