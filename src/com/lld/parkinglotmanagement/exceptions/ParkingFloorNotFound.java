package com.lld.parkinglotmanagement.exceptions;

@SuppressWarnings("serial")
public class ParkingFloorNotFound extends Exception{
    public ParkingFloorNotFound(String message) {
        super(message);
    }
}
