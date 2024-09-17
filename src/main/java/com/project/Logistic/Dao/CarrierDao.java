package com.project.Logistic.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Carrier;
import com.project.Logistic.Entity.Repository.CarrierRepo;

@Repository
public class CarrierDao {
	@Autowired
	private CarrierRepo carrierRepo;
	public Carrier saveCarrier(Carrier carrier) {
	    return carrierRepo.save(carrier);
	}

    public Carrier findById(int id) {
    	Optional<Carrier>optional=carrierRepo.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}else {
    		return null;
    	}
		
    }

	
}
