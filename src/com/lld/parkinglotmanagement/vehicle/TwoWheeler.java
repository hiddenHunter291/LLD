package com.lld.parkinglotmanagement.vehicle;

public class TwoWheeler implements Vehicle{
	private final String driverLicense;
	private final String vehicleId;
	private final String driverName;
	private static final VehicleType vehicleType = VehicleType.TWO_WHEELER;
	
	TwoWheeler(String driverLicense, String vehicleId, String driverName) {
		this.driverLicense = driverLicense;
		this.vehicleId = vehicleId;
		this.driverName = driverName;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public String getDriverName() {
		return driverName;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}	
}
