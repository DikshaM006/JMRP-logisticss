package com.project.Logistic.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cargoName;
    private String cargoDescription;
    private long cargoWeight;
    private long cargoCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoDescription() {
		return cargoDescription;
	}
	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}
	public long getCargoWeight() {
		return cargoWeight;
	}
	public void setCargoWeight(long cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
	public long getCargoCount() {
		return cargoCount;
	}
	public void setCargoCount(long cargoCount) {
		this.cargoCount = cargoCount;
	}
    
    public Cargo() {
    	
    }
}
