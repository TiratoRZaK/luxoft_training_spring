package com.luxoft.springaop.lab5.exceptions;

public class ValidationException extends RuntimeException {
    private final String message;

    public ValidationException(String messageError) {
        message = messageError;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
