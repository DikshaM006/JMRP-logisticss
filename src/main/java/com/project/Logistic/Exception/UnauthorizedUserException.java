package com.project.Logistic.Exception;

public class UnauthorizedUserException extends RuntimeException{
	String message;
    public UnauthorizedUserException(String message) {
    	this.message = message;
    }
    @Override
    public String getMessage() {
    	// TODO Auto-generated method stub
    	return message;
    }
}
