package com.main.java.exceptions;

public class AlreadyExistsException extends CustomException{
    public AlreadyExistsException(String field, String errorCode, String message) {
        super(field, errorCode, message);
    }
}
