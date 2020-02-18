package com.yapikredi.permission.exception;

import com.yapikredi.permission.util.Translator;

public class PermissionDayNotEnoughException extends RuntimeException {

    private static final long serialVersionUID = -2807541999575920234L;
    private String errorMessage;

    public PermissionDayNotEnoughException() {
        super();
    }

    public PermissionDayNotEnoughException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = Translator.toLocale(errorMessage);
    }


}
