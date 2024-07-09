package com.lld.parkinglotmanagement.exceptions;

@SuppressWarnings("serial")
public class NoAvailableParkingSpot extends Exception{
    public NoAvailableParkingSpot(String message) {
        super(message);
    }
}
