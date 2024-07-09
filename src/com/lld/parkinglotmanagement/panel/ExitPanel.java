package com.lld.parkinglotmanagement.panel;

import com.lld.parkinglotmanagement.parkingticket.ParkingTicket;
import com.lld.parkinglotmanagement.place.ParkingLot;

public class ExitPanel {
	private final int position;
	private final String Id;
	private final String floorId;
	
	ExitPanel(final int position, final String Id, final String floorId) {
		this.position = position;
		this.Id = Id;
		this.floorId = floorId;
	}
	
	public int getPosition() {
		return position;
	}
	
	public String getId() {
		return Id;
	}
	
	public String getFloorId() {
		return floorId;
	}
	
	private long getBillAmount(final ParkingTicket parkingTicket) {
		return parkingTicket.getPricingStrategy().getFare(parkingTicket);
	}
	
	public void vacateParkingLot(final ParkingTicket parkingTicket) {
		try {
			ParkingLot.INSTANCE.getParkingFloorById(floorId).vacateParkingSpot(
					parkingTicket.getVehicle().getVehicleType(),
					parkingTicket.getParkingSpot()
			);
			long parkingFare = getBillAmount(parkingTicket);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
