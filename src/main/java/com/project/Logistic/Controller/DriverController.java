package com.project.Logistic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Logistic.Dto.DriverDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Entity.Driver;
import com.project.Logistic.Service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
	@Autowired
	private DriverService driverService;
	
	@PostMapping("/addDriver")
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@Valid @RequestBody DriverDto driverDto,@CookieValue String role) {
		return driverService.saveDriver(driverDto,role);
	}
	
	@PutMapping("/updateDriver/{driverId}")
	public ResponseEntity<ResponseStructure<Driver>> updateById(@Valid @RequestBody  DriverDto driverDto,@PathVariable int driverId){
		return driverService.updateDriverById(driverDto, driverId);
	}
}
