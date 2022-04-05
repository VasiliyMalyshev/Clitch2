package com.malyshev.clitch.exception_handling;

public class AlreadySentException extends RuntimeException {
    public AlreadySentException(String message) {
        super(message);
    }
}