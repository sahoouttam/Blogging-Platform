package com.blog.platform.payload.request;

import com.blog.platform.entity.Pin;
import com.blog.platform.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {

    private String title;

    private User user;

    private List<Pin> pins;
}
