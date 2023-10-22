package org.epam.grpctask.service;

public class BookDoesNotExistException extends RuntimeException {

    public BookDoesNotExistException(String message) {
        super(message);
    }
}
