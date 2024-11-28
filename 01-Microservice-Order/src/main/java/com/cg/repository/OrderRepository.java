package com.cg.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.model.Order;

@org.springframework.stereotype.Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllByOrderno(int orderno);

}