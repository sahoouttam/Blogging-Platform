package com.blog.platform.payload.response;

import com.blog.platform.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteResponse {

    private Long id;

    private String name;

    private List<PostDto> posts;
}
