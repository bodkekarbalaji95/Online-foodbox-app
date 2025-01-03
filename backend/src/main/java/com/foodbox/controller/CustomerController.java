package com.foodbox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.foodbox.model.Customer;
import com.foodbox.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
public class CustomerController {
	//To create the instance of customerrepo

	@Autowired
	private CustomerRepository customerRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	//adding the customer details to db
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer, HttpSession session) {
		session.setAttribute("cust_email", customer.getEmail());
		return customerRepository.save(customer);
	}
	
	@SuppressWarnings("rawtypes")
	@CrossOrigin(origins = "http://localhost:4200")
	//To verify the credentials of customer login
	@PostMapping("/customers/{email}")
	public boolean verifyLogin(@RequestBody Map loginData, @PathVariable(name = "email") String email, HttpSession session) {
		String lemail = (String) loginData.get("email");
		String lpassword = (String) loginData.get("password");
		Customer customer = customerRepository.findByEmail(email);
		if(customer!= null && customer.getEmail().equals(lemail) && customer.getPassword().equals(lpassword)) {
			session.setAttribute("cust_email", lemail);
			return true;
		}else {
			return false; 
		}
	}
	//To retrive all the customer records
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	//To search for the customer
	@GetMapping("/customers/search/{keyword}")
	public List<Customer> searchCustomer(@PathVariable String keyword){
		return customerRepository.searchCustomer(keyword);
	}
	
	//To update he details of the customer
	@PutMapping("/customer/{email}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String email, @RequestBody Customer coustomerDetails) {
        Customer customer = customerRepository.findByEmail(email);
        customer.setPassword(coustomerDetails.getPassword());
        customer.setAddress(coustomerDetails.getAddress());
        customer.setContact(coustomerDetails.getContact());
        customer.setName(coustomerDetails.getName());
        Customer updatecust = customerRepository.save(customer);
        return ResponseEntity.ok(updatecust);
    }
	//To delete the customer record using email
	
	@DeleteMapping("/customers/{email}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable String email){
		Customer customer = customerRepository.findByEmail(email);
		customerRepository.delete(customer);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(map);
	}
	//To retrieve the customer record using specific email
	
	@GetMapping("/customers/{cust_email}")
	public Customer getCustomer(@PathVariable String cust_email) {
		return customerRepository.findByEmail(cust_email);
		
	}
}
