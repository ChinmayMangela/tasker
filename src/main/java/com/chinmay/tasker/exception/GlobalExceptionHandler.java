package com.chinmay.tasker.exception;

import com.chinmay.tasker.dto.exception.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation Failed",
                        System.currentTimeMillis(),
                        errors
                ),
                HttpStatus
                        .BAD_REQUEST
        );
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public static ResponseEntity<ErrorResponseDto> handleTaskNotFoundException(TaskNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        System.currentTimeMillis(),
                        Collections.emptyMap()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}