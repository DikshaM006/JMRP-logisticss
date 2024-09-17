package com.project.Logistic.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Carrier {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String carrierCompanyName;
	private String carrierContact;
	private String carrierEmail;
	@OneToMany(mappedBy = "carrier")
	private List<Driver> driver;

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

	public List<Driver> getDriver() {
		return driver;
	}

	public void setDriver(List<Driver> driver) {
		this.driver = driver;
	}


	public Carrier() {

	}
}
