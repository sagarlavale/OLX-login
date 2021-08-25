package com.zensar.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.UNAUTHORIZED)
public class EmptyTokenException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String message;

    public EmptyTokenException() {
        this.message = "";
    }

    public EmptyTokenException(String error)
    {
        this.message = error;
    }

    @Override
    public String toString() {
        return "Token Not Provided : "+ this.message;
    }
}
