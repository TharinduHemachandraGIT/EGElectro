package com.egcustomer.services;

import java.util.List;

import com.egcustomer.entity.Customer;

public interface ICustomerManagement {
	
	List<Customer> getCustomers();
	Customer createCustomer(Customer customer);
	Customer updateCustomer(int customerId, Customer customer);
	Customer getCustomer(int customerId);
	boolean deleteCustomer(int customerId);

}
