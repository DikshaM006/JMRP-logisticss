package com.project.Logistic.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.Logistic.Dao.DriverDao;
import com.project.Logistic.Dto.DriverDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Entity.Carrier;
import com.project.Logistic.Entity.Driver;
import com.project.Logistic.Entity.User;
import com.project.Logistic.Entity.Repository.CarrierRepo;
import com.project.Logistic.Exception.IdNotFoundException;
import com.project.Logistic.Exception.ResourceNotFoundException;
import com.project.Logistic.Exception.UnauthorizedUserException;

@Service
public class DriverService {

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private CarrierRepo carrierRepo;

    public ResponseEntity<ResponseStructure<Driver>> saveDriver(DriverDto driverDto, String role) {
    	if(role.equals("Admin")) {
    		 throw new UnauthorizedUserException("You are not authorized to perform this action.");
        }
    	
        // Fetch the Carrier by carrierId
        Carrier carrier = carrierRepo.findById(driverDto.getCarrierId())
            .orElseThrow(() -> new ResourceNotFoundException("Carrier not found"));

        // Create a new Driver entity and set the details
        Driver driver = new Driver();
        driver.setDriverName(driverDto.getDriverName());
        driver.setDriverPhoneNumber(driverDto.getDriverPhoneNumber());
        driver.setTruckRegisteredNumber(driverDto.getTruckRegisteredNumber());
        driver.setCarrier(carrier);  // Associate the Carrier with the Driver

        // Save the Driver entity to the database
        Driver savedDriver = driverDao.saveDriver(driver);

        // Prepare the response structure
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setMessage("success");
        responseStructure.setData(savedDriver);  // Include the saved Driver with the associated Carrier

        // Return the response entity with the status code
        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    	
    }
    
    public ResponseEntity<ResponseStructure<Driver>> updateDriverById(DriverDto driverDto, int driverId) {
        // Fetch existing driver using driverId, throwing exception if not found
        Driver existingDriver = driverDao.findById(driverId);
         if(existingDriver==null) {
        	 throw new IdNotFoundException("Driver ID not found");
         }

        // Update the driver's details from the DTO
        if (driverDto.getDriverName() != null) {
            existingDriver.setDriverName(driverDto.getDriverName());
        }
        if (driverDto.getDriverPhoneNumber() != null) {
            existingDriver.setDriverPhoneNumber(driverDto.getDriverPhoneNumber());
        }
        if (driverDto.getTruckRegisteredNumber() != null) {
            existingDriver.setTruckRegisteredNumber(driverDto.getTruckRegisteredNumber());
        }

        // Fetch and set the Carrier, throwing exception if Carrier ID not found
        Carrier carrier = carrierRepo.findById(driverDto.getCarrierId())
            .orElseThrow(() -> new IdNotFoundException("Carrier ID not found"));
        existingDriver.setCarrier(carrier);

        // Save the updated driver
        Driver updatedDriver = driverDao.saveDriver(existingDriver);

        // Prepare the response structure
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Driver updated successfully");
        responseStructure.setData(updatedDriver);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

    	
}
