package com.turkcell.spring_starter.exception;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
