package com.names.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoConnectionException extends RuntimeException {

    @ExceptionHandler(value = NoConnectionException.class)
    public ResponseEntity<String> exception() {
        return new ResponseEntity<>("Unable to establish connection", HttpStatus.NOT_FOUND);
    }
}
