package com.Week4HW.Week4.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){};

    public ResourceNotFoundException(String message){
        super(message);
    }
}
