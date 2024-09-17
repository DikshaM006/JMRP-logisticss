package com.project.Logistic.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Truck;
import com.project.Logistic.Entity.Repository.TruckRepo;

@Repository
public class TruckDao {
	@Autowired
	private TruckRepo truckRepo;

	public Truck saveTruck(Truck truck) {
         return truckRepo.save(truck);
	}
	
	public Truck getById(int id) {
		Optional<Truck>optional=truckRepo.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}else {
    		return null;
    	}
	}
}
