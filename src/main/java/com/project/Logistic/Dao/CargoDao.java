package com.project.Logistic.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Cargo;
import com.project.Logistic.Entity.Repository.CargoRepo;

@Repository
public class CargoDao {
	@Autowired
	private CargoRepo cargoRepo;

	public Cargo saveCargo(Cargo cargo) {
         return cargoRepo.save(cargo);
	}
	
	public Cargo getById(int id) {
		Optional<Cargo>optional=cargoRepo.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}else {
    		return null;
    	}
	}
}
