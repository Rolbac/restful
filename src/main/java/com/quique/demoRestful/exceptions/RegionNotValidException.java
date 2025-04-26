package com.quique.demoRestful.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegionNotValidException extends RuntimeException{

    public RegionNotValidException(String message){
        super(message);
    }
}
