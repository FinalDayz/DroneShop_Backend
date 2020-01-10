package com.github.service;

public class InvalidAccountException extends Throwable {

    private String message;

    public InvalidAccountException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
