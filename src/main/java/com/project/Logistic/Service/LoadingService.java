package com.project.Logistic.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.Logistic.Dao.LoadingDao;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Dto.CarrierDto;
import com.project.Logistic.Dto.LoadingDto;
import com.project.Logistic.Entity.Address;
import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.User;
import com.project.Logistic.Entity.Driver;
import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.Repository.AddressRepo;
import com.project.Logistic.Exception.IdNotFoundException;
import com.project.Logistic.Exception.UnauthorizedUserException;
@RestController
public class LoadingService {
	  @Autowired
	    private LoadingDao loadingDao;
	    
	    @Autowired
	    private AddressRepo addressRepo;

	    public ResponseEntity<ResponseStructure<Loading>> saveLoading(LoadingDto loadingDto, String role) {
	    	if(role.equalsIgnoreCase("Admin")||role.equalsIgnoreCase("User")) {
	        Optional<Address> addressOptional = addressRepo.findById(loadingDto.getAddressId());
	        if (!addressOptional.isPresent()) {
	            ResponseStructure<Loading> responseStructure = new ResponseStructure<>();
	            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	            responseStructure.setMessage("Address not found");
	            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	        }
	        
	        Address address = addressOptional.get();

	        // Create User entity from UserDto
	        Loading loading = new Loading();
	        loading.setCompanyName(loadingDto.getCompanyName());
	        loading.setLoadingDate(loadingDto.getLoadingDate());
	        loading.setLoadingTime(loadingDto.getLoadingTime());
	        loading.setAddress(address);
	        
	        Loading receivedLoading = loadingDao.saveLoading(loading);

	        ResponseStructure<Loading> responseStructure = new ResponseStructure<>();
	        responseStructure.setStatusCode(HttpStatus.CREATED.value());
	        responseStructure.setMessage("success");
	        responseStructure.setData(receivedLoading);
	        
	        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	    	}else {
	    		throw new UnauthorizedUserException("Only Amin and User can perform this action");
	    	}
	    }
	    
	    public ResponseEntity<ResponseStructure<Loading>> updateLoading(LoadingDto loadingDto, Integer loadingId, String role) {
	    	if(role.equalsIgnoreCase("Admin")||role.equalsIgnoreCase("User")) {
	    	
	    	// Retrieve the existing carrier by ID
	        Loading existingLoading = loadingDao.findById(loadingId);

	        if (existingLoading == null) {
	            throw new IdNotFoundException("Loading ID not found");
	        }

	        // Update the carrier's details
	        existingLoading.setCompanyName(loadingDto.getCompanyName());
	        existingLoading.setLoadingDate(loadingDto.getLoadingDate());
	        existingLoading.setLoadingTime(loadingDto.getLoadingTime());

	        // Handle the drivers list
	        Optional<Address> addressOptional = addressRepo.findById(loadingDto.getAddressId());
	        if (!addressOptional.isPresent()) {
	            ResponseStructure<Loading> responseStructure = new ResponseStructure<>();
	            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	            responseStructure.setMessage("Address not found");
	            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	        }
	        Address address = addressOptional.get();

	        // Save the updated carrier
	        Loading updatedLoading = loadingDao.saveLoading(existingLoading);

	        // Prepare and return the response
	        ResponseStructure<Loading> responseStructure = new ResponseStructure<>();
	        responseStructure.setStatusCode(HttpStatus.OK.value());
	        responseStructure.setMessage("Carrier updated successfully");
	        responseStructure.setData(updatedLoading);

	        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	    	 }else {
	      	   throw new UnauthorizedUserException("You are not Admin nor User");
	         }
	    }
}
