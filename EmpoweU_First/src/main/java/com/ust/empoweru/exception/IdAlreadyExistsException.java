package com.ust.empoweru.exception;

public class IdAlreadyExistsException extends RuntimeException{
    public IdAlreadyExistsException(String msg){
        super(msg);
    }
}
