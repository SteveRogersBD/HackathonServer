package com.example.HackathonServer.exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
}
