package com.project.Logistic.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.Logistic.Dao.CarrierDao;
import com.project.Logistic.Dto.CarrierDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Entity.Carrier;
import com.project.Logistic.Entity.Driver;
import com.project.Logistic.Entity.Repository.DriverRepo;
import com.project.Logistic.Exception.IdNotFoundException;
import com.project.Logistic.Exception.UnauthorizedUserException;

@Service
public class CarrierService {
    @Autowired
    private CarrierDao carrierDao;
    
    @Autowired
    private DriverRepo driverRepo;

    public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(CarrierDto carrierDto, String role) {
       if(role.equalsIgnoreCase("Admin")||role.equalsIgnoreCase("User")) {
    	// Create a list to hold the drivers
        List<Driver> drivers = new ArrayList<>();
        List<Integer> driverIds = carrierDto.getDriverId(); // Ensure driverId is not null
        
        // Check if the driver IDs list is not empty
        if (driverIds != null && !driverIds.isEmpty()) {
            for (Integer driverId : driverIds) {
                // Retrieve each driver by ID
                Driver driver = driverRepo.findById(driverId)
                                          .orElseThrow(() -> new IdNotFoundException("Driver ID not found: " + driverId));
                drivers.add(driver);
            }
        } else {
            System.out.println("No driver IDs provided or list is empty.");
        }

        // Create a new Carrier entity
        Carrier carrier = new Carrier();
        carrier.setCarrierCompanyName(carrierDto.getCarrierCompanyName());
        carrier.setCarrierContact(carrierDto.getCarrierContact());
        carrier.setCarrierEmail(carrierDto.getCarrierEmail());
        carrier.setDriver(drivers); // Set the list of drivers

        // Save the carrier
        Carrier savedCarrier = carrierDao.saveCarrier(carrier);

        // Prepare the response structure
        ResponseStructure<Carrier> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setMessage("Carrier created successfully");
        responseStructure.setData(savedCarrier);

        // Return the response entity
        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
       }else {
    	   throw new UnauthorizedUserException("You are not Admin nor User");
       }
    }
    
    public ResponseEntity<ResponseStructure<Carrier>> updateCarrier(CarrierDto carrierDto, Integer carrierId, String role) {
        if(role.equalsIgnoreCase("Admin")) {
    	// Retrieve the existing carrier by ID
        Carrier existingCarrier = carrierDao.findById(carrierId);

        if (existingCarrier == null) {
            throw new IdNotFoundException("Carrier ID not found");
        }

        // Update the carrier's details
        existingCarrier.setCarrierCompanyName(carrierDto.getCarrierCompanyName());
        existingCarrier.setCarrierContact(carrierDto.getCarrierContact());
        existingCarrier.setCarrierEmail(carrierDto.getCarrierEmail());

        // Handle the drivers list
        List<Driver> updatedDrivers = new ArrayList<>();
        List<Integer> driverIds = carrierDto.getDriverId();

        if (driverIds != null && !driverIds.isEmpty()) {
            for (Integer driverId : driverIds) {
                Driver driver = driverRepo.findById(driverId)
                                          .orElseThrow(() -> new IdNotFoundException("Driver ID not found: " + driverId));
                updatedDrivers.add(driver);
            }
        } else {
            System.out.println("No driver IDs provided or list is empty.");
        }
        existingCarrier.setDriver(updatedDrivers);

        // Save the updated carrier
        Carrier updatedCarrier = carrierDao.saveCarrier(existingCarrier);

        // Prepare and return the response
        ResponseStructure<Carrier> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Carrier updated successfully");
        responseStructure.setData(updatedCarrier);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }else {
        	throw new UnauthorizedUserException("You are not Admin");
        }
    }
}
