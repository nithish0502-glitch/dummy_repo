package com.examly.springapp.exception;

public class SeatsExceededException extends RuntimeException {
    public SeatsExceededException(String message) {
        super(message);
    }
}
