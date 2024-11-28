package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.User;
import com.cg.repository.UserRepository;


@Service
public class UserService implements IUserService{
	
	@Autowired
	UserRepository userrepo;
	
	
	public List<User> findAllUsers() {
		return userrepo.findAll();
		
	}
	

	public Optional<User> findUserById(int uid) {
		return userrepo.findById(uid);
	}
   
	public User createUser(User u) {
		return userrepo.save(u);
		
	}
	
	public String deleteUser(int uid) {
	    if (userrepo.existsById(uid)) {
	       userrepo.deleteById(uid);
	         return "deleted";
	    } else {
	    	 return "id not found";
	    }
	}
	
	public String updateUser(int id, User u) {
	    if (userrepo.existsById(id)) {
	        User product = new User(); 
	        product.setUid(id); 
	        product.setUsername(u.getUsername());
	        product.setPassword(u.getPassword());
	      
	        userrepo.save(product); 
	        return "Product updated successfully";
	    } else {
	        return "Product ID not found";
	    }
	}
	

	public List<User> getUserByName(String name){
		return userrepo.findAllByUsername(name);
		
	}




  
}


