package com.cg.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.exception.BadRequestException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.User;
import com.cg.service.IUserService;
import jakarta.validation.Valid;


@RequestMapping("/api")
@RestController
public class UserController {
	@Autowired
	IUserService userservice;
	
	
	
	
	@GetMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> getOrders(){
		return userservice.findAllUsers();
	}
	
	@GetMapping("/users/name/{name}")
	   public List<User> getMyUserByName(@PathVariable String name) {
		   return userservice.getUserByName(name);
	   }
	
	@GetMapping("/users/{id}")
 	Optional<User>findByUserIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException
 	{	Optional<User> user = userservice.findUserById(id);
 	
     	user.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
     	System.out.println(id);
 		return user;
 	}
   
   
   @PostMapping("/users")
   public User createMyUser(@Valid @RequestBody User u)throws BadRequestException
   { 
	   if(u.getUsername()==" " || u.getPassword()==" ") {
		   throw new BadRequestException("invalid details");
		   
	   }
 		return userservice.createUser(u);
 	}
  

   
   @DeleteMapping(path = "/users/{id}")
   public String deleteUserId(@PathVariable int id) throws ResourceNotFoundException {
	   Optional<User> user = userservice.findUserById(id);
	   user.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
       String result = userservice.deleteUser(id);
       return result; // Returns true if deleted, false if not found
   }
   
   
   @PutMapping(path = "/users/{id}")
   public String updateUser(@PathVariable int id, @RequestBody User user) throws BadRequestException{
	   if(user.getUsername()==" " || user.getPassword()==" ") {
		   throw new BadRequestException("invalid details");
		   }
	   
       return userservice.updateUser(id, user);
   }
   
   
   

   
 

	
    
}










//@PostMapping("/products/{id}")
//public User createMyUser(@PathVariable String id ,@RequestBody User user) throws BadRequestException{
//	if (!id.matches("\\d+")) {
//        throw new BadRequestException("User ID must be a numeric value.");
//    }
//
//    Integer orderId = Integer.valueOf(id);
//   
//	if (user == null) {
//        throw new BadRequestException("Product cannot be null.");
//	}
//	 user.setUid(orderId);
// 		return userservice.createUser(user);
// 	}

//
//@PostMapping("/users/dto")
//public UserDTO createUser(@Valid @RequestBody UserDTO userDTO)
//{
//	       User user = userDTO.toEntity();  // Convert DTO to Entity
//	        User createdUser = userservice.createUser(user);
//	        return UserDTO.fromEntity(createdUser);  // Return as DTO
//}
//
//
//@GetMapping("/users/dto/{id}")
//public UserDTO getOrderById(@PathVariable int id) throws ResourceNotFoundException {
//   return userservice.findUserById(id)
//                       .map(UserDTO::fromEntity)  
//                       .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
//}
//
//
//@GetMapping("/users/dto")        
//public List<UserDTO> getAllOrders() {
//    return userservice.findAllUsers()
//                        .stream()
//                        .map(UserDTO::fromEntity)  // Convert to ProductDTO
//                        .collect(Collectors.toList());
//}

//@Autowired
//IProductService productService;
//
//
//@GetMapping("/users/products")
//   public List<Product> getAllProducts() {
//       return productService.findAllProducts();  
//   }
//