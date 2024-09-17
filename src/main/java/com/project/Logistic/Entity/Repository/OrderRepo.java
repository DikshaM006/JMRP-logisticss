package com.project.Logistic.Entity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Logistic.Entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{

}
