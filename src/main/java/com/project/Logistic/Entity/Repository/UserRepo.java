package com.project.Logistic.Entity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Logistic.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUserName(String userName);

}
