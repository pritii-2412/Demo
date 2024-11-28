package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.model.Product;

public interface IProductService {
	
	List<Product> findAllProducts() ;
	Optional<Product> findProductById(int pid);
	Product createProduct(Product p);
	public List<Product> getProductByName(String name);
	public String deleteProduct(int pid);
	public String updateProduct(int id, Product u) ;
	
    
	
}
