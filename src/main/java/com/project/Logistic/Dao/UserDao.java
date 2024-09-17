package com.project.Logistic.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.User;
import com.project.Logistic.Entity.Repository.UserRepo;
import com.project.Logistic.Exception.IdNotFoundException;

@Repository
public class UserDao {
	@Autowired
	private UserRepo userRepo;
    public User saveUser(User user) {
    	return userRepo.save(user);
    }
    public User findById(int id) {
    	Optional<User>optional=userRepo.findById(id);
    	if(optional.isPresent()) {
    		return optional.get();
    	}else {
    		return null;
    	}
		
    }
    public String deleteById(int userId) {
        // Check if the user exists by using findById
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new IdNotFoundException("User Id Not Found"));

        // Delete the user
        userRepo.deleteById(userId);

        // Return a success message
        return "User with ID " + userId + " has been deleted successfully.";
    }
    
    public List<User> getAllUsers(){
    	return userRepo.findAll();
    }

}
