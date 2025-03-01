package com.example.HackathonServer.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
}
