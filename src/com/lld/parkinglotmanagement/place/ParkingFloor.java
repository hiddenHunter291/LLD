package com.lld.parkinglotmanagement.place;

import java.util.*;

import com.lld.parkinglotmanagement.exceptions.EntryPanelNotFound;
import com.lld.parkinglotmanagement.exceptions.NoAvailableParkingSpot;
import com.lld.parkinglotmanagement.exceptions.VehicleTypeNotCompatible;
import com.lld.parkinglotmanagement.panel.EntryPanel;
import com.lld.parkinglotmanagement.parkingspot.ParkingSpot;
import com.lld.parkinglotmanagement.parkingspot.ParkingSpotManager;
import com.lld.parkinglotmanagement.parkingspot.ParkingSpotType;
import com.lld.parkinglotmanagement.vehicle.VehicleType;

public class ParkingFloor {
	private final String floorId;
	private Map<ParkingSpotType, ParkingSpotManager> floorManager;
	private List<EntryPanel> entryPanels;
	
	ParkingFloor(final String floorId) {
		this.floorId = floorId;
		this.entryPanels = new ArrayList<>();
		this.floorManager = new HashMap<>();
		floorManager.put(ParkingSpotType.TWO_WHEELER_PARKING_SPOT, new ParkingSpotManager());
		floorManager.put(ParkingSpotType.FOUR_WHEELER_PARKING_SPOT, new ParkingSpotManager());
	}
	
	public String getId() {
		return floorId;
	}
	
	public ParkingSpot getParkingSpot(final String entryPanelId, final VehicleType vehicleType) throws NoAvailableParkingSpot, VehicleTypeNotCompatible, EntryPanelNotFound {
		Optional<EntryPanel> entryPanel = entryPanels
				.stream()
				.filter(panel -> panel.getId().equals(entryPanelId))
				.findFirst();
		
		if(entryPanel.isEmpty()) {
			throw new EntryPanelNotFound("panel with given id not Found");
		}
		
		final int referencePosition = entryPanel.get().getPosition();
		final ParkingSpot parkingSpot;
		
		if(vehicleType == VehicleType.TWO_WHEELER) {
			parkingSpot = floorManager.get(ParkingSpotType.TWO_WHEELER_PARKING_SPOT).getParkingSpot(referencePosition);
		} else if (vehicleType == VehicleType.FOUR_WHEELER) {
			parkingSpot = floorManager.get(ParkingSpotType.FOUR_WHEELER_PARKING_SPOT).getParkingSpot(referencePosition);
		} else {
			throw new VehicleTypeNotCompatible("vehicle-type not allowed");
		}
		
		return parkingSpot;
	}
	
	public void vacateParkingSpot(final VehicleType vehicleType, final ParkingSpot parkingSpot) throws VehicleTypeNotCompatible {
		if(vehicleType == VehicleType.TWO_WHEELER) {
			floorManager.get(ParkingSpotType.TWO_WHEELER_PARKING_SPOT).vacateParkingSpot(parkingSpot);
		} else if (vehicleType == VehicleType.FOUR_WHEELER) {
			floorManager.get(ParkingSpotType.FOUR_WHEELER_PARKING_SPOT).vacateParkingSpot(parkingSpot);
		} else {
			throw new VehicleTypeNotCompatible("vehicle-type not allowed");
		}
	}
}
