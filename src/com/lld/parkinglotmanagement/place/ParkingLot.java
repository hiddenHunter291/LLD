package com.lld.parkinglotmanagement.place;

import java.util.List;
import java.util.Optional;
import com.lld.parkinglotmanagement.exceptions.ParkingFloorNotFound;

public class ParkingLot {
	public static final ParkingLot INSTANCE = new ParkingLot();
	private ParkingLot() {};
	
	private List<ParkingFloor> parkingFloors;
	
	public List<ParkingFloor> getParkingFloors() {
		return parkingFloors;
	}

	public void setParkingFloors(List<ParkingFloor> parkingFloors) {
		this.parkingFloors = parkingFloors;
	}

	public ParkingFloor getParkingFloorById(String floorId) throws ParkingFloorNotFound {
		Optional<ParkingFloor> parkingFloor = parkingFloors
				.stream()
				.filter(floor -> floor.getId().equals(floorId))
				.findFirst();
		if(parkingFloor.isEmpty()) {
			throw new ParkingFloorNotFound("parking floor not found");
		}
		return parkingFloor.get();
	}
	
}
