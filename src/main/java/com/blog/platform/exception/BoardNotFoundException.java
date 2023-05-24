package com.blog.platform.exception;

import java.io.Serial;

public class BoardNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BoardNotFoundException(String message) {
        super(message);
    }
}
