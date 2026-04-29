package com.turkcell.spring_starter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.turkcell.spring_starter.dto.ErrorResponse;
import com.turkcell.spring_starter.dto.ValidationErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

   // Catches ANY exception that extends our base BusinessException
   @ExceptionHandler({BusinessException.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ErrorResponse handleBusinessException(BusinessException exception) {
        return new ErrorResponse(
            "Business Rule Violation", 
            "business-exception", 
            exception.getMessage()
        );
   }

   @ExceptionHandler({MethodArgumentNotValidException.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setArguments(errors);
        
        return response;
   }

   // Fallback for unexpected errors
   @ExceptionHandler({Exception.class})
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public ErrorResponse handleException(Exception exception) {
        return new ErrorResponse(
            "Internal Server Error", 
            "server-error", 
            "Beklenmedik bir hata oluştu: " + exception.getMessage()
        );
   }
}