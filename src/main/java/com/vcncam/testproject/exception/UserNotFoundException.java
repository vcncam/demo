package com.vcncam.testproject.exception;

public class UserNotFoundException extends CustomException{
    
    public UserNotFoundException() {
        super("401", "User Not Found");
    }
}
