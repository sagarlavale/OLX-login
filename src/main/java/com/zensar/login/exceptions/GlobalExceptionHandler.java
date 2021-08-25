package com.zensar.login.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity handleUserAlreadyExistsException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }

    @ExceptionHandler(value = {EmptyTokenException.class})
    public ResponseEntity handleEmptyTokenException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }

    @ExceptionHandler(value = {InvalidCredentialsException.class})
    public ResponseEntity handleInvalidCredentialsException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity handleTokenExpiredException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }
}
