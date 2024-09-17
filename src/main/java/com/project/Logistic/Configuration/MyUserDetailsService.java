package com.project.Logistic.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Logistic.Entity.User;
import com.project.Logistic.Entity.Repository.UserRepo;
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user=userRepo.findByUserName(userName);
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User Not found");
		}
		
		return new  UserPrincipal(user);
	}

}
