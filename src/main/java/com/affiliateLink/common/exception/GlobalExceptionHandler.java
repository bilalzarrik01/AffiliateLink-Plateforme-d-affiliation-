package com.affiliateLink.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String handleEmailAlreadyExists(EmailAlreadyExistsException ex){
        return ex.getMessage();
    }
    
@ExceptionHandler(PublisherNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public String handlePublisherNotFound(PublisherNotFoundException ex) {
    return ex.getMessage();
}


}