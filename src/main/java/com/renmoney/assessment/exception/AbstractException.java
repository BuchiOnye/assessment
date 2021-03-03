package com.renmoney.assessment.exception;


public class AbstractException extends RuntimeException {
    String code;


    public AbstractException(String code, String message) {
        super(message);
        this.code = code;
    }

    public AbstractException(String message) {
        super(message);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
