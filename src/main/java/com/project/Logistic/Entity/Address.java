package com.project.Logistic.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String streetName;
	private int houseNumber;
	private int areaPincode;
	private String district;
	private String state;
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getAreaPincode() {
		return areaPincode;
	}

	public void setAreaPincode(int areaPincode) {
		this.areaPincode = areaPincode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address() {

	}

	public Address(int id, String streetName, int houseNumber, int areaPincode, String district, String state,
			String country) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.areaPincode = areaPincode;
		this.district = district;
		this.state = state;
		this.country = country;
	}

}
