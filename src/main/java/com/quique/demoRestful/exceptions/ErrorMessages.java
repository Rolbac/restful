package com.quique.demoRestful.exceptions;

public enum ErrorMessages {
    REGION_NOT_FOUND("Region not Found.");

    private final String message;


    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
