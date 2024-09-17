package com.project.Logistic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Logistic.Dto.CarrierDto;
import com.project.Logistic.Dto.DriverDto;
import com.project.Logistic.Dto.LoadingDto;
import com.project.Logistic.Dto.OrderDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Dto.UnloadingDto;
import com.project.Logistic.Dto.UserDto;
import com.project.Logistic.Entity.Address;
import com.project.Logistic.Entity.Cargo;
import com.project.Logistic.Entity.Carrier;
import com.project.Logistic.Entity.Driver;
import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.Order;
import com.project.Logistic.Entity.Truck;
import com.project.Logistic.Entity.Unloading;
import com.project.Logistic.Entity.User;
import com.project.Logistic.Service.AddressService;
import com.project.Logistic.Service.CargoService;
import com.project.Logistic.Service.CarrierService;
import com.project.Logistic.Service.DriverService;
import com.project.Logistic.Service.LoadingService;
import com.project.Logistic.Service.OrderService;
import com.project.Logistic.Service.TruckServices;
import com.project.Logistic.Service.UnloadingService;
import com.project.Logistic.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private TruckServices truckServices;

	@Autowired
	private CargoService cargoService;

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private CarrierService carrierService;

	@Autowired
	private LoadingService loadingService;
	
	@Autowired
	private UnloadingService unloadingService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken)request.getAttribute("_csrf");
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestBody UserDto userDto ,@CookieValue String role) {
		return userService.saveUser(userDto, role);
	}
	 
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable int userId,@CookieValue String role){
		return userService.updateUser(userDto,userId,role);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@PathVariable int userId,@CookieValue String role){
		return userService.deleteById(userId,role);
	}
	
	@GetMapping("/getAllUsers")
		public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(@CookieValue String role){
			return userService.getAllUsers(role);
		}

	@PostMapping("/addAddress")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody Address address) {
		return addressService.saveAddress(address);
	}

	@PostMapping("/addTruck")
	public ResponseEntity<ResponseStructure<Truck>> saveTruck(@Valid @RequestBody Truck truck,@CookieValue String role) {
		return truckServices.saveTruck(truck,role);
	}

	@PostMapping("/addCargo")
	public ResponseEntity<ResponseStructure<Cargo>> saveCargo(@Valid @RequestBody Cargo cargo) {
		return cargoService.saveCargo(cargo);
	}

	@PostMapping("/addDriver")
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@Valid @RequestBody DriverDto driverDto,@CookieValue String role) {
		return driverService.saveDriver(driverDto, role);
	}
	
	@PostMapping("/addCarrier")
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@Valid @RequestBody CarrierDto carrierDto,@CookieValue String role){
	    return carrierService.saveCarrier(carrierDto,role);
	}
	
	@PutMapping("/updateCarrier/{carrierId}")
	public ResponseEntity<ResponseStructure<Carrier>> updateCarrier(@Valid @RequestBody CarrierDto carrierDto,@PathVariable int carrierId,@CookieValue String role){
		return carrierService.updateCarrier(carrierDto, carrierId,role);
	}

	@PostMapping("/addLoading")
	public ResponseEntity<ResponseStructure<Loading>> saveLoading(@Valid @RequestBody LoadingDto loadingDto,@CookieValue String role){
		return loadingService.saveLoading(loadingDto,role);
	}
	
	@PutMapping("/updateLoading/{loadingId}")
	public ResponseEntity<ResponseStructure<Loading>> updateLoading(@Valid @RequestBody LoadingDto loadingDto,@PathVariable int loadingId,@CookieValue String role){
		return loadingService.updateLoading(loadingDto, loadingId,role);
	}
	
	@PostMapping("/addUnloading")
	public ResponseEntity<ResponseStructure<Unloading>> saveUnloading(@Valid @RequestBody UnloadingDto unloadingDto,@CookieValue String role){
		return unloadingService.saveUnloading(unloadingDto,role);
	}
	
	@PutMapping("/updateUnloading/{unloadingId}")
	public ResponseEntity<ResponseStructure<Unloading>> updateUnloading(@Valid @RequestBody UnloadingDto unloadingDto,@PathVariable int unloadingId,@CookieValue String role){
		return unloadingService.updateUnloading(unloadingDto, unloadingId,role);
	}
	
	@PostMapping("/addOrder")
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@Valid @RequestBody OrderDto orderDto,@CookieValue String role){
		return orderService.saveOrder(orderDto,role);
	}
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<ResponseStructure<List<Order>>> getAllOrders(@CookieValue String role){
		return orderService.getAllOrders(role);
	}
	
	@DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<ResponseStructure<Order>> deleteOrder(@PathVariable int orderId,@CookieValue String role){
		return orderService.deleteById(orderId,role);
	}
	
	@GetMapping("/")
	public String print() {
		return "Hello it's Diksha";
	}
	
	
}
