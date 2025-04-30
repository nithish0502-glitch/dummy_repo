package com.examly.springapp.exception;

public class DuplicateRestaurantException extends RuntimeException {
    public DuplicateRestaurantException(String message) {
        super(message);
    }
}



