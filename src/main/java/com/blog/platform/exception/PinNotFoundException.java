package com.blog.platform.exception;

import java.io.Serial;

public class PinNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PinNotFoundException(String message) {
        super(message);
    }
}
