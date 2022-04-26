package com.egcustomer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egcustomer.dao.ICustomerManagementDAO;
import com.egcustomer.entity.Customer;

@Service
public class CustomerManagement implements ICustomerManagement {
	
	@Autowired
	private ICustomerManagementDAO dao;

	@Override
	public List<Customer> getCustomers() {
		return dao.getCustomers();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return dao.createCustomer(customer);
	}

	@Override
	public Customer updateCustomer(int customerId, Customer customer) {
		return dao.updateCustomer(customerId, customer);
	}

	@Override
	public Customer getCustomer(int customerId) {
		return dao.getCustomer(customerId);
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		return dao.deleteCustomer(customerId);
	}


}
