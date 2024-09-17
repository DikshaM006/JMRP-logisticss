package com.project.Logistic.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Unloading {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String companyName;
	private LocalDate unloadingDate;
	private String unloadingTime;
	@OneToOne
	private Address Address;
	
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

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address address) {
		Address = address;
	}

	public Unloading() {
		
	}
}
