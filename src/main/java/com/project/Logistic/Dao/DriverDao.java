package com.project.Logistic.Dao;

import com.project.Logistic.Entity.Driver;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Repository.DriverRepo;

@Repository
public class DriverDao {
	@Autowired
	private DriverRepo driverRepo;

	public Driver saveDriver(Driver driver) {
		return driverRepo.save(driver);
	}

	public Driver findById(int id) {
		Optional<Driver> optional = driverRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
