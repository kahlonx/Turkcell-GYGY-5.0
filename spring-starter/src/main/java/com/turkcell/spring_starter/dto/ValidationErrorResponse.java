package com.turkcell.spring_starter.dto;

import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> arguments; 

    public ValidationErrorResponse() {
        super("Validation Error", "validation-error", "One or more fields are invalid.");
    }

    public Map<String, String> getArguments() { return arguments; }
    public void setArguments(Map<String, String> arguments) { this.arguments = arguments; }
}
