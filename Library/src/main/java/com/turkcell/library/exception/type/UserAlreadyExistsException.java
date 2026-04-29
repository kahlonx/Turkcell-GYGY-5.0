package com.turkcell.library.exception.type;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}