package com.yapikredi.permission.exception;

public class ValidationInputException extends RuntimeException {

    private String errorMessage;

    public ValidationInputException() {
        super();
    }

    public ValidationInputException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }


}
