package com.lld.parkinglotmanagement.parkingspot;

public class FourWheelerParkingSpot implements ParkingSpot{
	private final int position;
	private final String parkingSpotId;
	
	FourWheelerParkingSpot(int position, String parkingSpotId) {
		this.position = position;
		this.parkingSpotId = parkingSpotId;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public String getParkingSpotId() {
		return parkingSpotId;
	}
}
