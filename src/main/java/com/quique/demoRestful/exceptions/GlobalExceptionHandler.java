package com.quique.demoRestful.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RegionNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleRegionNotFoundException(RegionNotFoundException e){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
//    }

    @ExceptionHandler(RegionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleRegionNotFoundException(RegionNotFoundException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(RegionNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRegionNotValidException(RegionNotValidException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRegionNotValidConstraints(ConstraintViolationException e){
        //Only returns first error
        return new ErrorResponse(e.getConstraintViolations().iterator().next().getMessage());
    }
}
