package com.blog.platform.service;

import com.blog.platform.entity.Category;
import com.blog.platform.exception.CategoryNotFoundException;
import com.blog.platform.mapper.CategoryMapper;
import com.blog.platform.payload.request.CategoryRequest;
import com.blog.platform.payload.response.CategoryResponse;
import com.blog.platform.repository.CategoryRepository;
import com.blog.platform.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @CachePut(value = "categories")
    public ResponseEntity<HttpStatus> createCategory(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.convertCategoryRequest(categoryRequest);
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Cacheable(value = "categories")
    public ResponseEntity<HttpStatus> updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_NOT_FOUND));
        CategoryMapper.updateCategory(category, categoryRequest);
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Cacheable(value = "categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::convertCategory)
                .collect(Collectors.toList());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Cacheable(value = "category")
    public ResponseEntity<CategoryResponse> getCategoryById(Long id) {
        CategoryResponse category = categoryRepository.findById(id)
                .map(CategoryMapper::convertCategory)
                .orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_NOT_FOUND));

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @CacheEvict(value = "categories", allEntries = true)
    public ResponseEntity<HttpStatus> deleteAllCategories() {
        categoryRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CacheEvict(value = "categories")
    public ResponseEntity<HttpStatus> deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
