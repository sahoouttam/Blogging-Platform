package com.blog.platform.service;

import com.blog.platform.entity.Tag;
import com.blog.platform.exception.TagNotFoundException;
import com.blog.platform.mapper.TagMapper;
import com.blog.platform.payload.response.TagResponse;
import com.blog.platform.repository.TagRepository;
import com.blog.platform.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
@RequiredArgsConstructor
public class TagService {

    @Autowired
    private final TagRepository tagRepository;

    @Cacheable(value = "tag_name")
    public ResponseEntity<TagResponse> getTagByName(String name) {
        TagResponse tag = tagRepository.findByName(name)
                .map(TagMapper::convertTag)
                .orElseThrow(() -> new TagNotFoundException(Constants.TAG_NOT_FOUND));

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @Cacheable(value = "tags")
    public ResponseEntity<List<TagResponse>> getAllTags(LocalDate startDate, LocalDate endDate) {

        Sort sort = Sort.sort(Tag.class).by(Tag::getName).ascending();

        List<TagResponse> tags = tagRepository.findDistinctByCreatedBetween(startDate, endDate, sort)
                .stream()
                .map(TagMapper::convertTag)
                .collect(Collectors.toList());

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
