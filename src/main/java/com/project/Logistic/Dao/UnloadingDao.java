package com.project.Logistic.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Unloading;
import com.project.Logistic.Entity.Repository.UnloadingRepo;

@Repository
public class UnloadingDao {
	@Autowired
	private UnloadingRepo unloadingRepo;
    public Unloading saveUnloading(Unloading unloading) {
    	return unloadingRepo.save(unloading);
    }
    public Unloading findById(int id) {
    	Optional<Unloading>optional=unloadingRepo.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}else {
    		return null;
    	}
		
    }
}
