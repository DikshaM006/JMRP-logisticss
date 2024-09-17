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

import com.project.Logistic.Dto.LoadingDto;
import com.project.Logistic.Dto.OrderDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Dto.UnloadingDto;
import com.project.Logistic.Dto.UserDto;
import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.Order;
import com.project.Logistic.Entity.Unloading;
import com.project.Logistic.Entity.User;
import com.project.Logistic.Service.LoadingService;
import com.project.Logistic.Service.OrderService;
import com.project.Logistic.Service.UnloadingService;
import com.project.Logistic.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private LoadingService loadingService;
	
	@Autowired
	private UnloadingService unloadingService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestBody UserDto userDto ,@CookieValue String role) {
		return userService.saveUser(userDto, role);
	}

	@PostMapping("/addOrder")
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@Valid @RequestBody OrderDto orderDto,@CookieValue String role){
		return orderService.saveOrder(orderDto,role);
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
	
	
}
