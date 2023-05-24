package com.blog.platform.controller;

import com.blog.platform.payload.response.TagResponse;
import com.blog.platform.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TagController {

    @Autowired
    private final TagService tagService;

    @GetMapping("/tags/{name}")
    public ResponseEntity<TagResponse> getTagByName(@PathVariable String name) {
        return tagService.getTagByName(name);
    }

    @GetMapping("/tags/{startDate}/{endDate}")
    public ResponseEntity<List<TagResponse>> getAllTags(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return tagService.getAllTags(startDate, endDate);

    }
}
