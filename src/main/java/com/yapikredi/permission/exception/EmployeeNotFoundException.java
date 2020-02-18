package com.yapikredi.permission.exception;

import com.yapikredi.permission.util.Translator;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8762891691751127068L;

    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(Translator.toLocale(message));
    }
}
