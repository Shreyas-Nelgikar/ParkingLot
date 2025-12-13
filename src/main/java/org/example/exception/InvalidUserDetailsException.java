package org.example.exception;

public class InvalidUserDetailsException extends Throwable {
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
