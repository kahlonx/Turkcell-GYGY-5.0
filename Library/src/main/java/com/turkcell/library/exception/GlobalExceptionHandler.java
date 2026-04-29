package com.turkcell.library.exception;

import com.turkcell.library.exception.response.ErrorResponse;
import com.turkcell.library.exception.response.ValidationErrorResponse;
import com.turkcell.library.exception.type.BusinessException;
import com.turkcell.library.exception.type.NotFoundException;
import com.turkcell.library.exception.type.UserAlreadyExistsException;
import com.turkcell.library.exception.type.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles NotFoundException specifically with a 404 Status
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(
                "Not Found",
                "NotFoundException",
                exception.getMessage()
        );
    }

    // Handles UserAlreadyExists with a 409 Conflict Status
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return new ErrorResponse(
                "User Already Exists",
                "UserAlreadyExistsException",
                exception.getMessage()
        );
    }

    // Handles InvalidCredentials with a 401 Unauthorized Status
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidCredentialsException(InvalidCredentialsException exception) {
        return new ErrorResponse(
                "Invalid Credentials",
                "InvalidCredentialsException",
                exception.getMessage()
        );
    }

    // Catch-all for any other BusinessException with a 400 Bad Request
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException exception) {
        return new ErrorResponse(
                "Business Error",
                "BusinessException",
                exception.getMessage()
        );
    }

    // Intercepts validation errors from @Valid annotations on requests
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ValidationErrorResponse(
                "Validation Error",
                "ValidationException",
                "One or more validation errors occurred.",
                errors
        );
    }

    // Generic fallback for unhandled exceptions (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception exception) {
        return new ErrorResponse(
                "Internal Server Error",
                "Exception",
                "An unexpected error occurred. Please contact support." 
        );
    }
}