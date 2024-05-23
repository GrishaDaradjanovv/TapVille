package org.example.tapville.exceptions;

public class UsernameDuplicateException extends RuntimeException{

    public UsernameDuplicateException(String type,String attribute,String value){
        super(String.format("%s with %s %s already exists.", type, attribute, value));

    }

}
