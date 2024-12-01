package com.lld.movieticketingapplication.exceptions;

public class SeatNotFoundException extends Exception{
    public SeatNotFoundException() {
        super("requested seat not found");
    }
}
