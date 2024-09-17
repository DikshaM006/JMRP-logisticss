package com.project.Logistic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.Logistic.Dao.CargoDao;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Entity.Cargo;

@RestController
public class CargoService {
	@Autowired
	private CargoDao cargoDao;
	
    public ResponseEntity<ResponseStructure<Cargo>> saveCargo(Cargo cargo) {
    	Cargo receivedCargo=cargoDao.saveCargo(cargo);
    	
    	ResponseStructure<Cargo>responseStructure=new ResponseStructure<>();
    	responseStructure.setStatusCode(HttpStatus.CREATED.value());
    	responseStructure.setMessage("success");
    	responseStructure.setData(receivedCargo);
    	
    	return new ResponseEntity<ResponseStructure<Cargo>>(responseStructure,HttpStatus.CREATED);
    }
}
