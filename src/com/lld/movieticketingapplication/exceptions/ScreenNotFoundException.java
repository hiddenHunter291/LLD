package com.lld.movieticketingapplication.exceptions;

public class ScreenNotFoundException extends Exception{
    public ScreenNotFoundException() {
        super("requested screen not found");
    }
}
