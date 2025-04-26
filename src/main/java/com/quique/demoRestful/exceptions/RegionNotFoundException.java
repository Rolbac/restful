package com.quique.demoRestful.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegionNotFoundException extends RuntimeException{

    public RegionNotFoundException() {
        super(ErrorMessages.REGION_NOT_FOUND.getMessage());
    }
}
