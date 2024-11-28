package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.model.Order;

public interface IOrderService {
	
	public List<Order> findAllOrders();
	public Optional<Order> findOrderById(int oid);
	public Order createOrder(Order o);
	public String deleteOrder(int oid);
	public String updateOrder(int id, Order u);
	public List<Order> getOrderByOrderno(int orderno);

}
