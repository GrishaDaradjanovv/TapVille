package org.example.tapville.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(String.format("%s", message));
    }

    public EntityNotFoundException(String type, String attribute, String value) {
        super(String.format("%s with %s %s was not found.", type, attribute, value));
    }
}
