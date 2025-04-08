package com.example.helloworld.exception;

public class InvalidUserActionException extends RuntimeException {
    public InvalidUserActionException(String message) {
        super(message);
    }
}
