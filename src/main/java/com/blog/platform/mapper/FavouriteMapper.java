package com.blog.platform.mapper;

import com.blog.platform.entity.Favourite;
import com.blog.platform.payload.response.FavouriteResponse;

import java.util.stream.Collectors;

public class FavouriteMapper {

    public static FavouriteResponse convertFavourite(Favourite favourite) {
        return FavouriteResponse.builder()
                .id(favourite.getId())
                .name(favourite.getName())
                .posts(favourite.getPosts().stream()
                        .map(PostMapper::convertPost)
                        .collect(Collectors.toList()))
                .build();
    }
}
