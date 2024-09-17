package com.project.Logistic.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Logistic.Entity.Order;
import com.project.Logistic.Entity.Repository.OrderRepo;
import com.project.Logistic.Exception.ResourceNotFoundException;

@Repository
public class OrderDao {
	@Autowired
	private OrderRepo orderRepo;

	public Order saveOrder(Order order) {
		return orderRepo.save(order);
	}

	public Order findById(int id) {
		Optional<Order> optional = orderRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	public String deleteById(int orderId) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));

		orderRepo.deleteById(orderId);

		return "Deleted Successfully";
	}
	
	public Order updateOrder(Order order, int orderId) {
	    // Fetch the existing order using the orderId, or throw an exception if not found
	    Order existingOrder = orderRepo.findById(orderId)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));

	    Order updatedOrder = orderRepo.save(existingOrder);
	    
	    return updatedOrder;
	}

}
