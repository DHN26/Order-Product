package io.vcti.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vcti.product.entity.Product;
import io.vcti.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@PostMapping("/save")
	public ResponseEntity<?> saveProduct(@RequestBody Product product)
	{
		service.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully.");
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<List<Product>> fetchAll()
	{
		List<Product> listOfProduct= service.fetchAll();
		return ResponseEntity.ok(listOfProduct);
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> fetchById(@PathVariable int id)
	{
		Product product=service.findById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(product);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable long id,@RequestBody Product p)
	{
		Product updated=service.updateProduct(id, p.getStatus());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated product : "+updated);
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id)
	{
		service.deleteById(id);
		return ResponseEntity.ok("Product deleted successfully");
	}
	
	@GetMapping("/sort")
	public ResponseEntity<List<Product>> sortByPrice()
	{
		List<Product> sortedProducts=service.sortByPrice();
		return ResponseEntity.ok(sortedProducts);
	}
	
	@GetMapping("/bulkUpdate/{discount}")
	public ResponseEntity<String> updateAll(@PathVariable int discount)
	{
		int noOfOrdersAffected=service.updateAll(discount);
		return ResponseEntity.ok("Discount applied successfully for "+noOfOrdersAffected+" orders.");
	}
}
