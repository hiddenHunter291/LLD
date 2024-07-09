package com.lld.parkinglotmanagement.exceptions;

@SuppressWarnings("serial")
public class VehicleTypeNotCompatible extends Exception{
	public VehicleTypeNotCompatible(String message) {
		super(message);
	}
}
