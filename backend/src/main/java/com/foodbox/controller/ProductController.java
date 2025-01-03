package com.foodbox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.model.Admin;
import com.foodbox.model.Product;
import com.foodbox.repository.AdminRepository;
import com.foodbox.repository.ProductRepository;

import com.foodbox.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class ProductController {
	//creating the instance of the productrepo

	@Autowired
	private ProductRepository productRepository;
	//creating the instance of the adminrepo

	@Autowired
	AdminRepository adminRepository;
	//Retrieving all the products added by the admin

	@GetMapping("/products/Admin")
	public List<Product> getAdminProducts() {
		return productRepository.findAll();
	}
	//retrieving all the available products

	@GetMapping("/products/cust")
	public List<Product> getAllProducts() {
		List<Product> prodList = productRepository.findIfAvail();
		if (prodList.isEmpty()) {
			List<Admin> adminList = adminRepository.findAll();
			if (adminList.isEmpty()) {
				adminRepository.save(new Admin("admin", "password"));
			}
			prodList = productRepository.findIfAvail();
		}
		return prodList;
	}
	//method to add the products if empty

	public void addProdIfEmpty(Product product) {
		int min = 10000;
		int max = 99999;
		int b = (int) (Math.random() * (max - min + 1) + min);
		product.setId(b);
		float temp = (product.getActualPrice()) * (product.getDiscount() / 100);
		float price = product.getActualPrice() - temp;
		product.setPrice(price);
		productRepository.save(product);
	}
	//To add the new products to the database

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		int min = 10000;
		int max = 99999;
		int b = (int) (Math.random() * (max - min + 1) + min);
		product.setId(b);
		float temp = (product.getActualPrice()) * (product.getDiscount() / 100);
		float price = product.getActualPrice() - temp;
		product.setPrice(price);
		return productRepository.save(product);
	}
	//To update the product details

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with " + id));
		product.setName(productDetails.getName());
		product.setDesc(productDetails.getDesc());
		product.setCategory(productDetails.getCategory());
		product.setImagepath(productDetails.getImagepath());
		product.setActualPrice(productDetails.getActualPrice());
		product.setDiscount(productDetails.getDiscount());
		product.setAvail(productDetails.getAvail());
		float temp = (product.getActualPrice()) * (product.getDiscount() / 100);
		float price = product.getActualPrice() - temp;
		product.setPrice(price);

		Product updatedProd = productRepository.save(product);
		return ResponseEntity.ok(updatedProd);

	}
	//To delete the product by using the product id

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found with " + id));
		productRepository.delete(product);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(map);
	}
	//To retrieve the specific product details

	@GetMapping("products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found with " + id));
		return ResponseEntity.ok(product);
	}
	//To search for the product

	@GetMapping("products/search/{keyword}")
	public List<Product> getSearchProducts(@PathVariable String keyword) {
		return productRepository.homeSearch(keyword);
	}
	//To retrieve all the veg items

	@GetMapping("products/Veg")
	public List<Product> getVeg() {
		return productRepository.getVeg();
	}
	//To retrieve all the non veg items

	@GetMapping("products/NonVeg")
	public List<Product> getNonVeg() {
		return productRepository.getNonVeg();
	}
	//To retrieve all the deserts

	@GetMapping("products/Dessert")
	public List<Product> getDessert() {
		return productRepository.getDessert();
	}

	
	
}