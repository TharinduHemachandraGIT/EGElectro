package com.egcustomer.dao;

import java.util.List;

import com.egcustomer.entity.Customer;

public interface ICustomerManagementDAO {

	List<Customer> getCustomers();
	Customer getCustomer(int customerId);
	Customer createCustomer(Customer customer);
	Customer updateCustomer(int customerId,Customer customer);
	boolean deleteCustomer(int customerId);
	
}
