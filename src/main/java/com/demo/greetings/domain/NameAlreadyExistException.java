package com.demo.greetings.domain;

public class NameAlreadyExistException extends RuntimeException {
    public NameAlreadyExistException(String message) {
        super(message);
    }

    public static NameAlreadyExistException withName(String username) {
        return new NameAlreadyExistException("A greeting with name " + username + " already exists. Try another.");
    }

}
