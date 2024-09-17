package com.project.Logistic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.Logistic.Dao.TruckDao;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Entity.Truck;
import com.project.Logistic.Exception.UnauthorizedUserException;

@Service
public class TruckServices {
	@Autowired
	private TruckDao truckDao;
	
    public ResponseEntity<ResponseStructure<Truck>> saveTruck(Truck truck, String role) {
    	if(role.equalsIgnoreCase("Admin")) {
    	Truck receivedTruck=truckDao.saveTruck(truck);
    	
    	ResponseStructure<Truck>responseStructure=new ResponseStructure<>();
    	responseStructure.setStatusCode(HttpStatus.CREATED.value());
    	responseStructure.setMessage("success");
    	responseStructure.setData(receivedTruck);
    	
    	return new ResponseEntity<ResponseStructure<Truck>>(responseStructure,HttpStatus.CREATED);
    }else {
    	throw new UnauthorizedUserException("You are not Admin");
    }
    }
}
