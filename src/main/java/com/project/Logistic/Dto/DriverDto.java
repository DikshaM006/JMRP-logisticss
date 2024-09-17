package com.project.Logistic.Dto;

import jakarta.validation.constraints.Size;

public class DriverDto {
	private int id;
	@Size(min=4,message="username must be minimum of 4 characters")
	private String driverName;
	@Size(min=10,message="PhoneNumber must be minimum of 10 characters")
	private String driverPhoneNumber;
	@Size(min=5,message="PhoneNumber must be minimum of 5 characters")
	private String truckRegisteredNumber;
	private int carrierId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}

	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}

	public String getTruckRegisteredNumber() {
		return truckRegisteredNumber;
	}

	public void setTruckRegisteredNumber(String truckRegisteredNumber) {
		this.truckRegisteredNumber = truckRegisteredNumber;
	}

	public int getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public DriverDto(int id, String driverName, String driverPhoneNumber, String truckRegisteredNumber, int carrierId) {
		super();
		this.id = id;
		this.driverName = driverName;
		this.driverPhoneNumber = driverPhoneNumber;
		this.truckRegisteredNumber = truckRegisteredNumber;
		this.carrierId = carrierId;
	}

}
