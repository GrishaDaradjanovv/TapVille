package org.example.tapville.exceptions;

import javax.naming.AuthenticationException;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message){
        super(message);
    }
}
