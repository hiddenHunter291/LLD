package com.lld.movieticketingapplication.exceptions;

public class ShowNotFoundException extends Exception{
    public ShowNotFoundException() {
        super("requested show not found");
    }
}
