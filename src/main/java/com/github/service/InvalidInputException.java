package com.github.service;

public class InvalidInputException extends Throwable {

    private String message;

    public InvalidInputException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
