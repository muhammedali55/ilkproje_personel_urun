package com.muhammet.ilkproje.exceptions;

import lombok.Getter;

@Getter
public class IlkprojeException extends RuntimeException{
    private final ErrorType errorType;
    public IlkprojeException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public IlkprojeException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
