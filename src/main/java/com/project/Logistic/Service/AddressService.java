package com.project.Logistic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Logistic.Dao.AddressDao;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Entity.Address;
import com.project.Logistic.Entity.Repository.AddressRepo;
import com.project.Logistic.Exception.IdNotFoundException;

@RestController
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private AddressRepo addressRepo;
	
    public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address) {
    	Address receivedAddress=addressDao.saveAddress(address);
    	
    	ResponseStructure<Address>responseStructure=new ResponseStructure<>();
    	responseStructure.setStatusCode(HttpStatus.CREATED.value());
    	responseStructure.setMessage("success");
    	responseStructure.setData(receivedAddress);
    	
    	return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.CREATED);
    }
    
    
}
