package com.examly.springapp.exception;

public class DuplicateDepartmentException extends RuntimeException {
    public DuplicateDepartmentException(String message) {
        super(message);
    }
}
