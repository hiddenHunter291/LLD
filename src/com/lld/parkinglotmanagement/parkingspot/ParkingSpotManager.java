package com.lld.parkinglotmanagement.parkingspot;

import java.util.*;

import com.lld.parkinglotmanagement.exceptions.NoAvailableParkingSpot;

public class ParkingSpotManager {
    private final ParkingSpotQueue availableParkingSpots;
    private final Set<ParkingSpot> inUseParkingSpots;

    public ParkingSpotManager() {
        availableParkingSpots = new ParkingSpotQueue();
        inUseParkingSpots = new HashSet<>();
    }
    
    public synchronized ParkingSpot getParkingSpot(final int referencePosition) throws NoAvailableParkingSpot {
    	ParkingSpot parkingSpot = availableParkingSpots.poll(referencePosition);
    	inUseParkingSpots.add(parkingSpot);
    	return parkingSpot;
    }

    public void addParkingSpot(final ParkingSpot inParkingSpot) {
    	Optional<ParkingSpot> parkingSpot = availableParkingSpots
    			.getAsList()
    			.stream()
    			.filter(ps -> ps.getPosition() == inParkingSpot.getPosition())
    			.findFirst();
    	if(parkingSpot.isPresent()) {
    		return;
    	}
    	availableParkingSpots.add(inParkingSpot);
    }

    public synchronized void vacateParkingSpot(final ParkingSpot inParkingSpot) {
        Optional<ParkingSpot> parkingSpot = availableParkingSpots
        		.getAsList()
                .stream()
                .filter(ps -> ps.getPosition() == inParkingSpot.getPosition())
                .findFirst();
        if(parkingSpot.isPresent()) {
            return;
        }
        inUseParkingSpots.remove(inParkingSpot);
        availableParkingSpots.add(inParkingSpot);
    }
    
}













