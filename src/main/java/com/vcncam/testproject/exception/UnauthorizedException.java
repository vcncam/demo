package com.vcncam.testproject.exception;

public class UnauthorizedException extends CustomException{
    
    public UnauthorizedException() {
        super("401", "You Are Not Authorized To Use This Function" );
    }
}
