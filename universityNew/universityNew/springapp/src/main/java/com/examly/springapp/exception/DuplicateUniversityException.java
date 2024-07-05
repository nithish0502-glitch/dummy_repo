package com.examly.springapp.exception;

public class DuplicateUniversityException extends RuntimeException {
    public DuplicateUniversityException(String message) {
        super(message);
    }
}
