package com.project.Logistic.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.Logistic.Entity.User;
import com.project.Logistic.Dao.OrderDao;
import com.project.Logistic.Dto.OrderDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Entity.Cargo;
import com.project.Logistic.Entity.Carrier;
import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.Order;
import com.project.Logistic.Entity.Unloading;
import com.project.Logistic.Entity.Repository.CargoRepo;
import com.project.Logistic.Entity.Repository.CarrierRepo;
import com.project.Logistic.Entity.Repository.LoadingRepo;
import com.project.Logistic.Entity.Repository.OrderRepo;
import com.project.Logistic.Entity.Repository.UnloadingRepo;
import com.project.Logistic.Entity.Repository.UserRepo;
import com.project.Logistic.Exception.IdNotFoundException;
import com.project.Logistic.Exception.ResourceNotFoundException;
import com.project.Logistic.Exception.UnauthorizedUserException;
@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private CarrierRepo carrierRepo;
	
	@Autowired
	private CargoRepo cargoRepo;
	
	@Autowired
	private LoadingRepo loadingRepo;
	
	@Autowired
	private UnloadingRepo unloadingRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public ResponseEntity<ResponseStructure<Order>> saveOrder(OrderDto orderDto, String role) {
        Optional<Carrier> carrierOptional = carrierRepo.findById(orderDto.getCarrierId());
        ResponseStructure<Order> responseStructure = new ResponseStructure<>();
        if((role.equals("Admin"))||(role.equals("User"))) {
        if (!carrierOptional.isPresent()) {
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Carrier not found");
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        Carrier carrier = carrierOptional.get();
        
        Optional<Cargo> cargoOptional = cargoRepo.findById(orderDto.getCargoId());
        if (!cargoOptional.isPresent()) {
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Carrier not found");
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        Cargo cargo = cargoOptional.get();

        Optional<Loading> loadingOptional = loadingRepo.findById(orderDto.getLoadingId());
        if (!loadingOptional.isPresent()) {
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Loading not found");
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        Loading loading = loadingOptional.get();
        
        Optional<Unloading> unloadingOptional = unloadingRepo.findById(orderDto.getUnloadingId());
        if (!unloadingOptional.isPresent()) {
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Unloading not found");
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        Unloading unloading = unloadingOptional.get();
        
        List<User> loadingUsers = new ArrayList<>();
		loadingUsers.add(userRepo.findById(orderDto.getLoadingUserId()).get());

		List<User> unloadingUsers = new ArrayList<>();
		unloadingUsers.add(userRepo.findById(orderDto.getUnloadingUserId()).get());
        
        Order order = new Order();
        order.setDateOfOrder(orderDto.getDateOfOrder());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setFreightCost(orderDto.getFreightCost());
        order.setAdditionalInfo(orderDto.getAdditionalInfo());
        order.setCarrier(carrier);
        order.setCargo(cargo);
        order.setLoading(loading);
        order.setUnloading(unloading);
        order.setUnloadingUser(unloadingUsers);
        order.setLoadingUser(loadingUsers);
        
        Order receivedUser = orderDao.saveOrder(order);

        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setMessage("success");
        responseStructure.setData(receivedUser);
        
        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        }else{
			throw new UnauthorizedUserException("You are not authorized to perform this action.");
		}
    }
	
	public ResponseEntity<ResponseStructure<List<Order>>> getAllOrders(String role){
		if(role.equalsIgnoreCase("Admin")) {
        List<Order> orders = orderDao.getAllOrders();
        
        ResponseStructure<List<Order>> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Orders fetched successfully");
        responseStructure.setData(orders);

        // Return the response entity
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}else {
			throw new UnauthorizedUserException("You are not Admin");
		}
	}
	
	public ResponseEntity<ResponseStructure<Order>> deleteById(int orderId, String role) {
		if(role.equalsIgnoreCase("Admin")) {
	    Order order = orderDao.findById(orderId);
	      if(order==null) {
	    	  throw new IdNotFoundException("Order Id not found");
	      }

	    // Perform the deletion
	    orderDao.deleteById(orderId);

	    // Create the response structure
	    ResponseStructure<Order> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Order deleted successfully");
	    responseStructure.setData(order);

	    // Return the response entity
	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}else {
			throw new UnauthorizedUserException("You are not Admin");
		}
	}

	public ResponseEntity<ResponseStructure<Order>> updateOrder(OrderDto orderDto, int orderId) {
		return null;
	    
	}

}
