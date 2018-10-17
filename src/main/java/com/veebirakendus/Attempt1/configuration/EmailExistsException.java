package com.veebirakendus.Attempt1.configuration;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message){
        super(message);
    }
}
