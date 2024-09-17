package com.project.Logistic.Exception;

public class IdNotFoundException extends RuntimeException{
	String message;
    public IdNotFoundException(String message) {
    	this.message = message;
    }
    @Override
    public String getMessage() {
    	return message;
    }
}
