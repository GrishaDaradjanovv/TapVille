package org.example.tapville.exceptions;

public class InvalidOperationException extends RuntimeException{

    public InvalidOperationException(String message){
        super(message);
    }
}
