package com.vinodh.booking.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Vinodh Kumar T
 */
public class SectionNotFoundException extends Exception {
    private HttpStatus httpStatus;

    public SectionNotFoundException() {
        this("Section not found", HttpStatus.NOT_FOUND);
    }

    public SectionNotFoundException(final String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public SectionNotFoundException(final String message,
                                   final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
