package com.example.blog.exceptions;

public class AlreadyInUseException extends RuntimeException{
    public AlreadyInUseException(String message) {
        super(message);
    }
}