package com.yapikredi.permission.exception;

public class PermissionAppException extends RuntimeException {
    private static final long serialVersionUID = -1977708418897168346L;


    public PermissionAppException() {
        super();
    }

    public PermissionAppException(String message) {
        super(message);
    }
}
