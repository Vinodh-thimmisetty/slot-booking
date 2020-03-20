package com.vinodh.booking.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Vinodh Kumar T
 */
public class UnAuthorizedException extends Exception {

    private HttpStatus httpStatus;

    public UnAuthorizedException() {
        this("user is not allowed to book slot(s)", HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedException(final String message) {
        this(message, HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedException(final String message,
                                 final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
