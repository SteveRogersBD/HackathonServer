package com.example.HackathonServer.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
    public PostNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
}
