package com.demo.greetings.domain;

public class NameExistsException extends RuntimeException {
    public NameExistsException(String message) {
        super(message);
    }

    public static NameExistsException withName(String username) {
        return new NameExistsException("A greeting with name " + username + " already exists. Try another.");
    }

}
