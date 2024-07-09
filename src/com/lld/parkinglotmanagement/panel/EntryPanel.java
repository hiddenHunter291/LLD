package com.lld.parkinglotmanagement.panel;


import com.lld.parkinglotmanagement.parkingspot.ParkingSpot;
import com.lld.parkinglotmanagement.parkingticket.ParkingTicket;
import com.lld.parkinglotmanagement.place.ParkingLot;
import com.lld.parkinglotmanagement.pricing.PricingStrategy;
import com.lld.parkinglotmanagement.vehicle.Vehicle;

public class EntryPanel {
	private final int position;
	private final String Id;
	private final String floorId;
	
	EntryPanel(final int position, final String Id, final String floorId) {
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
	
	public ParkingTicket getParkingTicket(final Vehicle vehicle, final PricingStrategy pricingStrategy) {
		ParkingTicket parkingTicket = null;
		try {
			ParkingSpot parkingSpot = ParkingLot.INSTANCE.getParkingFloorById(floorId).getParkingSpot(Id,
					vehicle.getVehicleType());
			ParkingTicket.Builder parkingTicketBuilder = new ParkingTicket.Builder();
			parkingTicket = parkingTicketBuilder
					.vehicle(vehicle)
					.pricingStrategy(pricingStrategy)
					.parkingSpot(parkingSpot)
					.issuedAt(System.currentTimeMillis())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingTicket;
	}
}
