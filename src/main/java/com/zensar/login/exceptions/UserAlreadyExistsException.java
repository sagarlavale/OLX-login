package com.zensar.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

    public UserAlreadyExistsException() {
        this.message = "";
    }

    public UserAlreadyExistsException(String error)
    {
        this.message = error;
    }

    @Override
    public String toString() {
        return "Invalid Request : "+ this.message;
    }
}
