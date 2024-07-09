package com.lld.parkinglotmanagement.parkingspot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.lld.parkinglotmanagement.exceptions.NoAvailableParkingSpot;

public class ParkingSpotQueue {
	private final List<ParkingSpot> parkingSpots;
	
	public ParkingSpotQueue() {
		this.parkingSpots = new ArrayList<>();
	}
	
    private boolean compareParkingSpots(final int referencePosition, final ParkingSpot ps1, final ParkingSpot ps2) {
        int ps1AbsPosition = Math.abs(ps1.getPosition() - referencePosition);
        int ps2AbsPosition = Math.abs(ps2.getPosition() - referencePosition);
        return ps1AbsPosition < ps2AbsPosition;
    }
	
    private void heapifyParkingSpots(int parentPosition, final int referencePosition) {
        int totalParkingSpots = parkingSpots.size();
        int leftChildPosition = parentPosition * 2 + 1;
        int rightChildPosition = parentPosition * 2 + 2;
        int nearestParkingSpotPosition = parentPosition;

        if(leftChildPosition < totalParkingSpots &&
                compareParkingSpots(
                		referencePosition,
                        parkingSpots.get(leftChildPosition),
                        parkingSpots.get(parentPosition))
        ) {
            parentPosition = leftChildPosition;
        }

        if(rightChildPosition < totalParkingSpots &&
                compareParkingSpots(
                        referencePosition,
                        parkingSpots.get(rightChildPosition),
                        parkingSpots.get(parentPosition))
        ) {
            parentPosition = rightChildPosition;
        }

        if(parentPosition != nearestParkingSpotPosition) {
            Collections.swap(parkingSpots, parentPosition, nearestParkingSpotPosition);
            heapifyParkingSpots(parentPosition, referencePosition);
        }
    }
	
	public ParkingSpot poll(final int referencePosition) throws NoAvailableParkingSpot {
        int totalParkingSpots = parkingSpots.size();

        if(totalParkingSpots == 0) {
            throw new NoAvailableParkingSpot("All Parking Spots are full");
        }

        for(int index = (totalParkingSpots / 2) - 1; index >= 0; index--) {
            heapifyParkingSpots(index, referencePosition);
        }
        
        ParkingSpot parkingSpot = parkingSpots.get(0);
        parkingSpots.remove(0);
        return parkingSpot;
	}
	
	public void add(final ParkingSpot parkingSpot) {
		parkingSpots.add(parkingSpot);
	}
	
	public List<ParkingSpot> getAsList() {
		return Collections.unmodifiableList(parkingSpots);
	}

}
