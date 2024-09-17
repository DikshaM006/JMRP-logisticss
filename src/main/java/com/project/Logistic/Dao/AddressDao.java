package com.project.Logistic.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Address;
import com.project.Logistic.Entity.Repository.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
   private AddressRepo addressRepo;
	public Address saveAddress(Address address) {
    	return addressRepo.save(address);
    }
    public Address getById(int id) {
    	Optional<Address>optional=addressRepo.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}else {
    		return null;
    	}
		
    }
}
