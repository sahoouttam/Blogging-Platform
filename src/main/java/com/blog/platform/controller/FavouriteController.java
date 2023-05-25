package com.blog.platform.controller;

import com.blog.platform.entity.Favourite;
import com.blog.platform.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FavouriteController {

    private final FavouriteService favouriteService;


}
