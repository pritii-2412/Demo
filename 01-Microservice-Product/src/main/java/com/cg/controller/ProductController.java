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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.exception.BadRequestException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Product;
import com.cg.service.IProductService;
import jakarta.validation.Valid;


@RequestMapping("/api")
@RestController
public class ProductController {
	
	@Autowired
	IProductService productservice;
	
	 @GetMapping(path="products")
	  public List<Product> getProducts(){
		  return productservice.findAllProducts();  
	  } 
	 
	 
	  @GetMapping("/products/name/{name}")
	   public List<Product> getMyProductByName(@PathVariable String name) {
		   return productservice.getProductByName(name);
	   }
	   
	
	

	  @GetMapping("/products/{id}")
		Optional<Product>findByProductIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException
		{	Optional<Product> product = productservice.findProductById(id);
		
	    	product.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
	    	System.out.println(id);
	    return product;	
		}
	   
		
		@PostMapping("/products/{id}")
		public Product createMyProduct(@PathVariable String id ,@RequestBody Product product) throws BadRequestException{
			if (id.matches("\\d+")) {
		        throw new BadRequestException("Product ID must be a numeric value.");
		    }

		    Integer orderId = Integer.valueOf(id);
		   
			if (product == null) {
		        throw new BadRequestException("Product cannot be null.");
			}
			 product.setPid(orderId);
		 		return productservice.createProduct(product);
		 	}
		
		
		
		@DeleteMapping(path = "/products/{id}")
		public String deleteOrderId(@PathVariable String id) throws BadRequestException {
		   
		    if (!id.matches("\\d+")) {
		        throw new BadRequestException("Product ID must be a numeric value.");
		    }

		    Integer orderId = Integer.valueOf(id);

		    String result = productservice.deleteProduct(orderId);
		    if (result == null) {
		        throw new BadRequestException("Product not found for ID: " + orderId);
		    }

		    return result;
		}
		
	  
		
		
		
	  
		@PutMapping(path = "/products/{id}")
		public String updateProduct(@PathVariable int id, @RequestBody Product product)   throws  ResourceNotFoundException{
			Optional<Product> p1 = productservice.findProductById(id);
			
	     p1.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
	    	return productservice.updateProduct(id, product);
		   }
		
	  
	   
	   
	   
	   
	   
	   
}
	


//@PutMapping(path = "/products/{id}")
//public String updateOrder(@PathVariable int id, @RequestBody Product product) {
//    return productservice.updateProduct(id, product);
//}

//
//@PostMapping("/products")
//public Product createMyProduct(@Valid @RequestBody Product product)
//{
//		return productservice.createProduct(product);
//	}




//		//6.fetching data by dto
//		@GetMapping("/products/dto")        //returning glass means required variables ex- just a glass of water from bucket
//	    public List<ProductDTO> getAllProducts() {
//	        return productservice.findAllProducts()
//	                            .stream()
//	                            .map(ProductDTO::fromEntity)  // Convert to ProductDTO
//	                            .collect(Collectors.toList());
//	    }
//		
//		//7.fetching data by dto filtering by id
//		@GetMapping("/products/dto/{id}")
//	    public ProductDTO getProductById(@PathVariable int id) throws ResourceNotFoundException {
//	        return productservice.findProductById(id)
//	                            .map(ProductDTO::fromEntity)  
//	                            .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
//	    }
//		
//	
//		 //8.creating or adding product by dto 
//		   @PostMapping("/products/dto/create")
//			public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO)
//			{
//				        Product product = productDTO.toEntity();  // Convert DTO to Entity
//				        Product createdProduct = productservice.createProduct(product);
//				        return ProductDTO.fromEntity(createdProduct);  // Return as DTO
//			}
//		   
		   

   
   /* @GetMapping(path = "/products/{id}")
   public Optional<Product>getByProductId(@PathVariable int id)
  {
		return productservice.findProductById(id);
	}*/
   
   
   /*@GetMapping(path = "/products/myProduct")
   public Optional<Product>getByProductIdReq(@RequestParam int id) 
  {//http://localhost:9090/api/products/myProduct?id=1 postman
		return productservice.findProductById(id);
	}
   
    * @PathVariable: Use it for things that are essential to the location (like specific items).
    * 
@RequestParam: Use it for optional details (like filters or search criteria).
 Users can get exactly what they want without fetching unnecessary data.*/
