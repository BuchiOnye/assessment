package com.buchi.assessment.exception;

public class UnauthorizedException extends AbstractException {

    public UnauthorizedException(String code, String message) {
        super(code, message);
    }
}
