package com.partylink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBusinessException(BusinessException exception){
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception){

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                        )
                );
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleGenericException(Exception exception) {

        System.err.println("🚨 UNHANDLED EXCEPTION DETECTED: " + exception.getClass().getName());
        exception.printStackTrace();

        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected internal error occurred. Our team has been notified."
        );
    }
}
