package com.examly.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(SeatsNotAvailableException.class)
    public ResponseEntity<String> seatsNotAvailableExceptionHandler(SeatsNotAvailableException seatsNotAvailableException) {
        return new ResponseEntity<>(seatsNotAvailableException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> usernameNotFoundExceptionHandler(UsernameNotFoundException usernameNotFoundException) {
        return new ResponseEntity<>(usernameNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericExceptionHandler(Exception genericException) {
        return new ResponseEntity<>(genericException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
