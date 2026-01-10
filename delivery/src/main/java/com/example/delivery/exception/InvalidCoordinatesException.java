package com.example.delivery.exception;

public class InvalidCoordinatesException extends RuntimeException {
    public InvalidCoordinatesException(String message) {
        super(message);
    }
}
