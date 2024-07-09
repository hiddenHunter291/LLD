package com.lld.parkinglotmanagement.parkingspot;

public class TwoWheelerParkingSpot implements ParkingSpot {
	private final int position;
	private final String parkingSpotId;
	
	TwoWheelerParkingSpot(int position, String parkingSpotId) {
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

