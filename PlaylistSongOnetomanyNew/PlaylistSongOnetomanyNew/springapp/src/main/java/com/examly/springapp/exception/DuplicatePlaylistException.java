package com.examly.springapp.exception;

public class DuplicatePlaylistException extends RuntimeException {
    public DuplicatePlaylistException(String message) {
        super(message);
    }
}
