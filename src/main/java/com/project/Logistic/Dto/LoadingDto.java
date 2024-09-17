package com.project.Logistic.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

public class LoadingDto {
	
	private int id;
	@Size(min=4,message="username must be minimum of 4 characters")
	private String companyName;
	private LocalDate loadingDate;
	private String loadingTime;
	private int addressId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(LocalDate loadingDate) {
		this.loadingDate = loadingDate;
	}
	public String getLoadingTime() {
		return loadingTime;
	}
	public void setLoadingTime(String loadingTime) {
		this.loadingTime = loadingTime;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
