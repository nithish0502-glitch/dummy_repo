package com.examly.springapp.exception;

public class AccountAccessDeniedException extends RuntimeException {
    public AccountAccessDeniedException(String message) {
        super(message);
    }
}



