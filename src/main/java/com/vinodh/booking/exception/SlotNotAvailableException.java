package com.vinodh.booking.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Vinodh Kumar T
 */
public class SlotNotAvailableException extends Exception {
    private HttpStatus httpStatus;

    public SlotNotAvailableException() {
        this("Slot filled by some one", HttpStatus.NOT_FOUND);
    }

    public SlotNotAvailableException(final String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public SlotNotAvailableException(final String message,
                                     final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
