package com.example.activity_trackerweek8.Exceptions;

import com.example.activity_trackerweek8.Models.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



//This page uses a more generic method to handle exceptions that may arise as the code proceeds
@ControllerAdvice
@ResponseStatus
public class GlobalErrorHandler {
    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<Errors> mapExceptions(CustomExceptions ex){
        Errors errors = new Errors(ex.getMessage());
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
