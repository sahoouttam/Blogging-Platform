package com.blog.platform.payload.request;

import com.blog.platform.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    private Long id;

    private String name;

    private List<Post> posts;
}
