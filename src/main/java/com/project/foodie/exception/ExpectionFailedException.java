package com.project.foodie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ExpectionFailedException extends RuntimeException {
    public ExpectionFailedException(String message) {
        super(message);
    }

    public ExpectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
