package com.vinodh.booking.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Vinodh Kumar T
 */
public class SlotNotFoundException extends Exception {
    private HttpStatus httpStatus;

    public SlotNotFoundException() {
        this("Slot not found", HttpStatus.NOT_FOUND);
    }

    public SlotNotFoundException(final String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public SlotNotFoundException(final String message,
                                 final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
