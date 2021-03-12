package com.buchi.assessment.exception;

public class InvalidCredentialsException extends AbstractException {
    public InvalidCredentialsException(String code, String message) {
        super(code, message);
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}