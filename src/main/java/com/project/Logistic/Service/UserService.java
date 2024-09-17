package com.project.Logistic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.Logistic.Dao.OrderDao;
import com.project.Logistic.Dao.UserDao;
import com.project.Logistic.Dto.LoadingDto;
import com.project.Logistic.Dto.ResponseStructure;
import com.project.Logistic.Dto.UserDto;
import com.project.Logistic.Entity.Address;
import com.project.Logistic.Entity.Loading;
import com.project.Logistic.Entity.User;
import com.project.Logistic.Entity.Repository.AddressRepo;
import com.project.Logistic.Exception.IdNotFoundException;
import com.project.Logistic.Exception.ResourceNotFoundException;
import com.project.Logistic.Exception.UnauthorizedUserException;

@RestController
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AddressRepo addressRepo;

//	@Autowired
//	AuthenticationManager authenticationManager;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public ResponseEntity<ResponseStructure<User>> saveUser(UserDto userDto, String role) {
		Optional<Address> addressOptional = addressRepo.findById(userDto.getAddressId());
		ResponseStructure<User> responseStructure = new ResponseStructure<>();

		if (role.equals("Admin")) {
			if (!addressOptional.isPresent()) {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("Address not found");
				return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
			}

			Address address = addressOptional.get();

			User user = new User();
			user.setUserName(userDto.getUserName());
			user.setUserPassword(encoder.encode(userDto.getUserPassword()));
			user.setUserPhoneNumber(userDto.getUserPhoneNumber());
			user.setUserRole(userDto.getUserRole());
			user.setAddress(address); // Set the Address object

			User receivedUser = userDao.saveUser(user);

			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("success");
			responseStructure.setData(receivedUser);

			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new UnauthorizedUserException("You are not authorized to perform this action.");
		}
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(UserDto userDto, Integer userId, String role) {
		if (role.equalsIgnoreCase("Admin")) {
			// Retrieve the existing user by ID
			User existingUser = userDao.findById(userId);
			if (existingUser == null) {
				throw new IdNotFoundException("User ID not found");
			}

			// Update the user's details
			existingUser.setUserName(userDto.getUserName());
			existingUser.setUserPassword(userDto.getUserPassword());
			existingUser.setUserPhoneNumber(userDto.getUserPhoneNumber());
			existingUser.setUserRole(userDto.getUserRole());

			// Handle the address update
			Optional<Address> addressOptional = addressRepo.findById(userDto.getAddressId());
			if (addressOptional.isPresent()) {
				Address address = addressOptional.get();
				existingUser.setAddress(address);
			} else {
				// Handle the case where the address is not found if needed
				throw new ResourceNotFoundException("Address not found with ID " + userDto.getAddressId());
			}

			// Save the updated user
			User updatedUser = userDao.saveUser(existingUser);

			// Prepare and return the response
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("User updated successfully");
			responseStructure.setData(updatedUser);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnauthorizedUserException("You are not Admin");
		}
	}

	public ResponseEntity<ResponseStructure<User>> deleteById(@PathVariable int userId, String role) {
		if (role.equalsIgnoreCase("Admin")) {
			User user = userDao.findById(userId);
			if (user == null) {
				throw new IdNotFoundException("User Id Not Found");
			}

			userDao.deleteById(userId);

			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("user deleted successfully");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnauthorizedUserException("You are not Admin");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(String role) {
		if (role.equalsIgnoreCase("Admin")) {
			List<User> users = userDao.getAllUsers();

			ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Users fetched successfully");
			responseStructure.setData(users);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnauthorizedUserException("You are not Admin");
		}
	}

}
