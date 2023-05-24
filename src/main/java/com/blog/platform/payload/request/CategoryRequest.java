package com.blog.platform.payload.request;

import com.blog.platform.entity.Post;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    private String name;

    private List<Post> posts;
}
