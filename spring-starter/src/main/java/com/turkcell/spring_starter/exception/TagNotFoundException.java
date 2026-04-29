package com.turkcell.spring_starter.exception;

public class TagNotFoundException extends BusinessException {
    public TagNotFoundException(String message) {
        super(message);
    }
}