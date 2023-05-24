package com.blog.platform.mapper;

import com.blog.platform.entity.Pin;
import com.blog.platform.entity.Tag;
import com.blog.platform.payload.request.PinRequest;
import com.blog.platform.payload.request.TagRequest;
import com.blog.platform.payload.response.PinResponse;
import com.blog.platform.payload.response.TagResponse;

import java.time.LocalDate;

public class TagMapper {

    public static TagResponse convertTag(Tag tag) {
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .createdOn(tag.getCreatedOn())
                .updatedOn(tag.getUpdatedOn())
                .build();
    }

    public static Tag convertTagRequest(TagRequest tagRequest) {
        return Tag.builder()
                .name(tagRequest.getName())
                .posts(tagRequest.getPosts())
                .createdOn(LocalDate.now())
                .updatedOn(LocalDate.now())
                .build();
    }

    public static void updateTag(Tag tag, TagRequest tagRequest) {
        tag.setName(tagRequest.getName());
        tag.setPosts(tagRequest.getPosts());
        tag.setCreatedOn(tag.getCreatedOn());
        tag.setUpdatedOn(LocalDate.now());
    }
}
