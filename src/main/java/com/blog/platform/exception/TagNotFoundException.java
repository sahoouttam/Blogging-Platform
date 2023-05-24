package com.blog.platform.exception;

import java.io.Serial;

public class TagNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TagNotFoundException(String message) {
        super(message);
    }
}
