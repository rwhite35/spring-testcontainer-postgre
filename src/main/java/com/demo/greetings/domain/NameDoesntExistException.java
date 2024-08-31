package com.demo.greetings.domain;

public class NameDoesntExistException extends RuntimeException {
    public NameDoesntExistException(String message) {
        super(message);
    }

    public static NameDoesntExistException withName(String username) {
        return new NameDoesntExistException("Name " + username + " doesnt exist. Is it the corrent name?");
    }
}
