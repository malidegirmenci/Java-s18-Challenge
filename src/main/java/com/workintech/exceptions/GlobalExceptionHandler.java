package com.workintech.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(LibraryException libraryException){
        ErrorResponse errorResponse = new ErrorResponse(libraryException.getMessage());
        log.error("Exception occurred: "+errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse,libraryException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException exception){
        List errorList = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    System.out.println();
                    Map<String,String> errorMap = new HashMap<>();
                    errorMap.put(
                            fieldError.getField(),
                            fieldError.getDefaultMessage()
                    );
                    log.error("Exception occurred: "+fieldError.getDefaultMessage());
                    return errorMap;
                }).toList();
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        log.error("Exception occurred "+errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
