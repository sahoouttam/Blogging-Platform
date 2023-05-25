package com.blog.platform.mapper;

import com.blog.platform.dto.PostDto;
import com.blog.platform.entity.Post;

public class PostMapper {

    public static PostDto convertPost(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .slug(post.getSlug())
                .body(post.getBody())
                .build();
    }
}
