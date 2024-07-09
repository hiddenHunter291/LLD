package com.lld.parkinglotmanagement.parkingticket;

import com.lld.parkinglotmanagement.parkingspot.ParkingSpot;
import com.lld.parkinglotmanagement.pricing.HourlyPricingStrategy;
import com.lld.parkinglotmanagement.pricing.PricingStrategy;
import com.lld.parkinglotmanagement.vehicle.Vehicle;

public class ParkingTicket {
    private final long issuedAt;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final PricingStrategy pricingStrategy;

	public static class Builder {
		private long issuedAt;
		private Vehicle vehicle;
        private ParkingSpot parkingSpot;
		private PricingStrategy pricingStrategy = new HourlyPricingStrategy();
		public Builder() {}

        public Builder issuedAt(final long issuedAt) {
			this.issuedAt = issuedAt;
			return this;
		}
		public Builder vehicle(final Vehicle vehicle) {
            this.vehicle = vehicle;
			return this;
		}
		public Builder parkingSpot(final ParkingSpot parkingSpot) {
			this.parkingSpot = parkingSpot;
			return this;
		}
		public Builder pricingStrategy(final PricingStrategy pricingStrategy) {
			this.pricingStrategy = pricingStrategy;
			return this;
		}
		public ParkingTicket build() {
			return new ParkingTicket(this);
		}
	}

	private ParkingTicket(Builder builder) {
		this.issuedAt = builder.issuedAt;
		this.parkingSpot = builder.parkingSpot;
		this.vehicle = builder.vehicle;
		this.pricingStrategy = builder.pricingStrategy;
	}

	public long getIssuedAt() {
		return issuedAt;
	}

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public PricingStrategy getPricingStrategy() {
		return pricingStrategy;
	}
}
