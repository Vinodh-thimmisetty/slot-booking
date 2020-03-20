package com.vinodh.booking.controller;

import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.SlotNotAvailableException;
import com.vinodh.booking.exception.SlotNotFoundException;
import com.vinodh.booking.exception.UnAuthorizedException;
import com.vinodh.booking.exception.UserNotFoundException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author Vinodh Kumar T
 */
@ControllerAdvice
public class ResponseUtil {

    @ExceptionHandler(value = OptimisticLockingFailureException.class)
    protected ResponseEntity<?> handleConflict(
            Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    protected ResponseEntity<?> invalidPrivileges(
            Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), new HttpHeaders(), UNAUTHORIZED);
    }

    @ExceptionHandler(value = SectionNotFoundException.class)
    protected ResponseEntity<?> invalidSection(
            Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), new HttpHeaders(), NOT_FOUND);
    }

    @ExceptionHandler(value = SlotNotFoundException.class)
    protected ResponseEntity<?> invalidSlot(
            Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<?> invalidUser(
            Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SlotNotAvailableException.class)
    protected ResponseEntity<?> slotFilled(
            Exception ex, WebRequest request) {
        return new ResponseEntity(ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }
}