package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Order;
import com.cg.repository.OrderRepository;


@Service
public class OrderService implements IOrderService{


	@Autowired
	OrderRepository orderrepo;
	
	
	public List<Order> findAllOrders() {
		return orderrepo.findAll();
		
	}
	

	public Optional<Order> findOrderById(int oid) {
		return orderrepo.findById(oid);
	}
   
	public Order createOrder(Order o) {
		return orderrepo.save(o);
		
	}
	
	public String deleteOrder(int oid) {
	    if (orderrepo.existsById(oid)) {
	       orderrepo.deleteById(oid);
	         return "deleted";
	    } else {
	    	 return "id not found";
	    }
	}
	
	public String updateOrder(int id, Order u) {
	    if (orderrepo.existsById(id)) {
	        Order product = new Order(); 
	        product.setOid(id); 
	        product.setOdate(u.getOdate());
	        product.setOrderno(u.getOrderno());
	      
	        orderrepo.save(u); 
	        return "Order updated successfully";
	    } else {
	        return "Order ID not found";
	    }
	}
	

	public List<Order> getOrderByOrderno(int orderno){
		return orderrepo.findAllByOrderno(orderno);
		
	}




  
}