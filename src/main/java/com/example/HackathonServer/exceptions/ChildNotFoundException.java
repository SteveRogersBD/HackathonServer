package com.example.HackathonServer.exceptions;

public class ChildNotFoundException extends RuntimeException {
    public ChildNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
    public ChildNotFoundException(String message) {
        super(message);
    }
}
