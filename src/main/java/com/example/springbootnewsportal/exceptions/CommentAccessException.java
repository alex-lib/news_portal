package com.example.springbootnewsportal.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CommentAccessException extends RuntimeException {

    public CommentAccessException(String message) {
        super(message);
    }
}