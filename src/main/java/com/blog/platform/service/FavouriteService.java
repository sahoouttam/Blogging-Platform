package com.blog.platform.service;

import com.blog.platform.mapper.FavouriteMapper;
import com.blog.platform.payload.response.FavouriteResponse;
import com.blog.platform.repository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;

    public ResponseEntity<List<FavouriteResponse>> getAllFavouriteByName(String name) {
        List<FavouriteResponse> favourites = favouriteRepository.findByName(name)
                .stream()
                .map(FavouriteMapper::convertFavourite)
                .sorted(Comparator.comparing(FavouriteResponse::getName))
                .collect(Collectors.toList());

        return new ResponseEntity<>(favourites, HttpStatus.OK);
    }
}
