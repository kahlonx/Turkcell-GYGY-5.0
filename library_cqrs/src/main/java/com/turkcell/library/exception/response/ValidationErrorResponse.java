package com.turkcell.library.exception.response;

import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors; // Maps field names to error messages

    public ValidationErrorResponse() {}

    public ValidationErrorResponse(String title, String type, String message, Map<String, String> errors) {
        super(title, type, message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() { return errors; }
    public void setErrors(Map<String, String> errors) { this.errors = errors; }
}