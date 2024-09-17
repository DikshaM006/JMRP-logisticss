package com.project.Logistic.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

public class UnloadingDto {
	private int id;
	@Size(min=4,message="username must be minimum of 4 characters")
	private String companyName;
	private LocalDate unloadingDate;
	private String unloadingTime;
	private int addressId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getUnloadingDate() {
		return unloadingDate;
	}
	public void setUnloadingDate(LocalDate unloadingDate) {
		this.unloadingDate = unloadingDate;
	}
	public String getUnloadingTime() {
		return unloadingTime;
	}
	public void setUnloadingTime(String unloadingTime) {
		this.unloadingTime = unloadingTime;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
}
