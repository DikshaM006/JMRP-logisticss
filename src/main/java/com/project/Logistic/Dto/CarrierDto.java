package com.project.Logistic.Dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class CarrierDto {
	private int id;
	@Size(min=4,message="username must be minimum of 4 characters")
	private String carrierCompanyName;
	@Size(min=10,message="PhoneNumber must be minimum of 10 characters")
	private String carrierContact;
	@Email
	private String carrierEmail;
	
	private List<Integer> driverId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarrierCompanyName() {
		return carrierCompanyName;
	}

	public void setCarrierCompanyName(String carrierCompanyName) {
		this.carrierCompanyName = carrierCompanyName;
	}

	public String getCarrierContact() {
		return carrierContact;
	}

	public void setCarrierContact(String carrierContact) {
		this.carrierContact = carrierContact;
	}

	public String getCarrierEmail() {
		return carrierEmail;
	}

	public void setCarrierEmail(String carrierEmail) {
		this.carrierEmail = carrierEmail;
	}

	public List<Integer> getDriverId() {
		return driverId;
	}

	public void setDriverIds(List<Integer> driverId) {
		this.driverId = driverId;
	}

	public CarrierDto(int id, String carrierCompanyName, String carrierContact, String carrierEmail,
			List<Integer> driverId) {
		super();
		this.id = id;
		this.carrierCompanyName = carrierCompanyName;
		this.carrierContact = carrierContact;
		this.carrierEmail = carrierEmail;
		this.driverId = driverId;
	}

	

	
	
}
