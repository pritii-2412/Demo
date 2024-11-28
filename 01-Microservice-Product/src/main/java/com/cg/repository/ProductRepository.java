package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
	public List<Product> findAllByPname(String name);
	
	 
	
}
//ProductRepository makes it easier to work with your product data in the database without 
//having to write all the complicated code yourself.