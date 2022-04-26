package com.egcustomer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egcustomer.entity.Customer;
import com.egcustomer.services.ICustomerManagement;

@Controller
@RequestMapping("customerservice")
public class CustomerManagementController {
	
	@Autowired
	private ICustomerManagement service;
	
	@GetMapping("customers")
	public ResponseEntity<List<Customer>> getCustomers(){
		
		List<Customer> customers = service.getCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		
	}
	
	@GetMapping("customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Integer id){
		Customer customer = service.getCustomer(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@PostMapping("customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		Customer b = service.createCustomer(customer);
		return new ResponseEntity<Customer>(b, HttpStatus.OK);
		
	}
	
	@PutMapping("customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
		
		Customer b = service.updateCustomer(id, customer);
		return new ResponseEntity<Customer>(b, HttpStatus.OK);
	}
	
	@DeleteMapping("customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id){
		boolean isDeleted = service.deleteCustomer(id);
		if(isDeleted){
			String responseContent = "Customer has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting customer from database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
