package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.model.Product;
import com.cg.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService implements IProductService{

	@Autowired
	ProductRepository productrepo;
	@Override
	public List<Product> findAllProducts() {
		return productrepo.findAll();
		
	}
	
	public Optional<Product> findProductById(int pid) {
		return productrepo.findById(pid);
	}
   
	public Product createProduct(Product p) {
		return productrepo.save(p);
		
	}
	
	public String deleteProduct(int pid) {
	    if (productrepo.existsById(pid)) {
	       productrepo.deleteById(pid);
	         return "deleted";
	    } else {
	    	 return "id not found";
	    }
	}
	
	public String updateProduct(int id, Product u) {
	    if (productrepo.existsById(id)) {
	        Product product = new Product(); 
	        product.setPid(u.getPid()); 
	        product.setPname(u.getPname());
	        product.setPrice(u.getPrice());
	      
	        productrepo.save(u); 
	        return "Order updated successfully";
	    } else {
	        return "Order ID not found";
	    }
	}
	
	
	public List<Product> getProductByName(String name){
		return productrepo.findAllByPname(name);
		
	}

	
  
}





