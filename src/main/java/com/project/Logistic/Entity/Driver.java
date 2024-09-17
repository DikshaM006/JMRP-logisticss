package com.project.Logistic.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String driverName;
	private String driverPhoneNumber;
	private String truckRegisteredNumber;

	@ManyToOne
	@JoinColumn(name = "carrier_id")
	@JsonIgnore
	private Carrier carrier;

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

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Driver(int id, String driverName, String driverPhoneNumber, String truckRegisteredNumber, Carrier carrier) {
		super();
		this.id = id;
		this.driverName = driverName;
		this.driverPhoneNumber = driverPhoneNumber;
		this.truckRegisteredNumber = truckRegisteredNumber;
		this.carrier = carrier;
	}
	public Driver() {
		
	}
}
