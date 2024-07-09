package com.lld.parkinglotmanagement.pricing;

import com.lld.parkinglotmanagement.parkingticket.ParkingTicket;

public interface PricingStrategy {
	long getFare(final ParkingTicket parkingTicket);
}
