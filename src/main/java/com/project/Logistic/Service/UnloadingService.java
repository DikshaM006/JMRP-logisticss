package com.project.Logistic.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.Logistic.Dao.UnloadingDao;
import com.project.Logistic.Dto.LoadingDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Dto.UnloadingDto;
import com.project.Logistic.Entity.Address;
import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.Unloading;
import com.project.Logistic.Entity.Repository.AddressRepo;
import com.project.Logistic.Exception.IdNotFoundException;
import com.project.Logistic.Exception.UnauthorizedUserException;

@RestController
public class UnloadingService {
	@Autowired
	private UnloadingDao unloadingDao;

	@Autowired
	private AddressRepo addressRepo;

	public ResponseEntity<ResponseStructure<Unloading>> saveUnloading(UnloadingDto unloadingDto, String role) {
		if(role.equalsIgnoreCase("Admin")|| role.equalsIgnoreCase("User")) {
		Optional<Address> addressOptional = addressRepo.findById(unloadingDto.getAddressId());
        if (!addressOptional.isPresent()) {
            ResponseStructure<Unloading> responseStructure = new ResponseStructure<>();
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Unloading not found");
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        
        Address address = addressOptional.get();

        // Create User entity from UserDto
        Unloading unloading = new Unloading();
        unloading.setCompanyName(unloadingDto.getCompanyName());
        unloading.setUnloadingDate(unloadingDto.getUnloadingDate());
        unloading.setUnloadingTime(unloadingDto.getUnloadingTime());
        unloading.setAddress(address); // Set the Address object
        
        Unloading receivedUnloading = unloadingDao.saveUnloading(unloading);

        ResponseStructure<Unloading> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setMessage("success");
        responseStructure.setData(receivedUnloading);
        
        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }else

	{
		throw new UnauthorizedUserException("You are not Admin");
	}
	}

	public ResponseEntity<ResponseStructure<Unloading>> updateUnloading(UnloadingDto unloadingDto,
			Integer unloadingId, String role) {
		if(role.equalsIgnoreCase("Admin")||role.equalsIgnoreCase("User")) {
		// Retrieve the existing carrier by ID
		Unloading existingUnloading = unloadingDao.findById(unloadingId);

		if (existingUnloading == null) {
			throw new IdNotFoundException("Unloading ID not found");
		}

		// Update the carrier's details
		existingUnloading.setCompanyName(unloadingDto.getCompanyName());
		existingUnloading.setUnloadingDate(unloadingDto.getUnloadingDate());
		existingUnloading.setUnloadingTime(unloadingDto.getUnloadingTime());

		// Handle the drivers list
		Optional<Address> addressOptional = addressRepo.findById(unloadingDto.getAddressId());
		if (!addressOptional.isPresent()) {
			ResponseStructure<Unloading> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Address not found");
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
		Address address = addressOptional.get();

		// Save the updated carrier
		Unloading updatedUnloading = unloadingDao.saveUnloading(existingUnloading);

		// Prepare and return the response
		ResponseStructure<Unloading> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Unloading updated successfully");
		responseStructure.setData(updatedUnloading);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}else {
			throw new UnauthorizedUserException("You are not Admin");
		}
	}
}
