package com.vinodh.booking.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Vinodh Kumar T
 */
public class UserNotFoundException extends Exception {

    private HttpStatus httpStatus;

    public UserNotFoundException() {
        this("User not found", HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(final String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(final String message,
                                 final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
