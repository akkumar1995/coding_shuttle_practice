package com.avinash.kumar.module2.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(NoSuchElementException ex){
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        ApiResponse<?> apiResponse = new ApiResponse.ApiResponseBuilder<>().data(null).timeStamp(LocalDateTime.now())
                .error(apiError).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
