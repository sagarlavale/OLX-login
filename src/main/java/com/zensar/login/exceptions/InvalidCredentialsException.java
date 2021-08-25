package com.zensar.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String message;

    public InvalidCredentialsException() {
        this.message = "";
    }

    public InvalidCredentialsException(String error)
    {
        this.message = error;
    }

    @Override
    public String toString() {
        return "Invalid Credentials : ";
    }
}
