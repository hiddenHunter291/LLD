package com.lld.parkinglotmanagement.pricing;

import com.lld.parkinglotmanagement.parkingticket.ParkingTicket;

public class HourlyPricingStrategy implements PricingStrategy{
	private static final long COST_PER_HOUR = 10;
	@Override
	public long getFare(final ParkingTicket parkingTicket) {
		long parkingLotEntryTime = parkingTicket.getIssuedAt();
		long parkingLotExitTime = System.currentTimeMillis();
		return COST_PER_HOUR * toHours(parkingLotExitTime - parkingLotEntryTime);
	}

	public long toHours(final long milliSeconds) {
		final long seconds = milliSeconds / 1000;
		final long minutes = seconds / 60;
		return minutes / 60;
	}
}
