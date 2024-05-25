package com.codesoft.udu.exception;

public class NullEntityReferenceException extends IllegalArgumentException {
    public NullEntityReferenceException(String message) {
        super(message);
    }
}
