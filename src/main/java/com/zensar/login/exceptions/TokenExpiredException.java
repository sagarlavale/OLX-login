package com.zensar.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.UNAUTHORIZED)
public class TokenExpiredException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String message;

    public TokenExpiredException() {
        this.message = "";
    }

    public TokenExpiredException(String error)
    {
        this.message = error;
    }

    @Override
    public String toString() {
        return "Token Expired : "+ this.message;
    }
}
