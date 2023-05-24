package com.blog.platform.exception;

import java.io.Serial;

public class CommentNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CommentNotFoundException(String message) {
        super(message);
    }
}
