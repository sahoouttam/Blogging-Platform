package com.blog.platform.mapper;

import com.blog.platform.entity.Category;
import com.blog.platform.payload.request.CategoryRequest;
import com.blog.platform.payload.response.CategoryResponse;

import java.time.LocalDate;

public class CategoryMapper {

    public static CategoryResponse convertCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .posts(category.getPosts())
                .build();
    }

    public static Category convertCategoryRequest(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .posts(categoryRequest.getPosts())
                .createdOn(LocalDate.now())
                .updatedOn(LocalDate.now())
                .build();
    }

    public static void updateCategory(Category category, CategoryRequest categoryRequest) {
        category.setName(categoryRequest.getName());
        category.setPosts(categoryRequest.getPosts());
        category.setCreatedOn(LocalDate.now());
        category.setUpdatedOn(LocalDate.now());
    }
}
