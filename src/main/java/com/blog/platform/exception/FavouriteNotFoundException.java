package com.blog.platform.exception;

import java.io.Serial;

public class FavouriteNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FavouriteNotFoundException(String message) {
        super(message);
    }
}
