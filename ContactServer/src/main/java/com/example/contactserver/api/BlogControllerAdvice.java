package com.example.contactserver.api;

import com.example.contactserver.exception.BadRequestException;
import com.example.contactserver.exception.EntityNotFoundException;
import com.example.contactserver.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BlogControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(BadRequestException ex) {
        Error error = new Error();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setDesc(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleEntityNotFoundException(EntityNotFoundException ex) {
        Error error = new Error();

        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setDesc(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
