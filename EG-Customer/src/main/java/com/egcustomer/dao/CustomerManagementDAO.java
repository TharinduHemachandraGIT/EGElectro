package com.egcustomer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.egcustomer.entity.Customer;

@Transactional
@Repository
public class CustomerManagementDAO implements ICustomerManagementDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers() {
		
		String hql = "FROM Customer as atcl ORDER BY atcl.id";
		return (List<Customer>) entityManager.createQuery(hql).getResultList();
	}

	
	@Override
	public Customer getCustomer(int customerId) {
		
		return entityManager.find(Customer.class, customerId);
	}

	
	@Override
	public Customer createCustomer(Customer customer) {
		entityManager.persist(customer);
		Customer b = getLastInsertedCustomer();
		return b;
	}

	
	@Override
	public Customer updateCustomer(int customerId, Customer customer) {
		
		//First We are taking Book detail from database by given book id and 
		// then updating detail with provided book object
		Customer customerFromDB = getCustomer(customerId);
		customerFromDB.setName(customer.getName());
		customerFromDB.setEmail(customer.getEmail());
		customerFromDB.setAddress(customer.getAddress());
		customerFromDB.setDistrict(customer.getDistrict());
		
		
		entityManager.flush();
		
		//again i am taking updated result of book and returning the book object
		Customer updatedCustomer = getCustomer(customerId);
		
		return updatedCustomer;
	}

	
	@Override
	public boolean deleteCustomer(int customerId) {
		Customer customer = getCustomer(customerId);
		entityManager.remove(customer);
		
		//we are checking here that whether entityManager contains earlier deleted book or not
		// if contains then book is not deleted from DB that's why returning false;
		boolean status = entityManager.contains(customer);
		if(status){
			return false;
		}
		return true;
	}
	
	
	private Customer getLastInsertedCustomer(){
		String hql = "FROM Customer as atcl ORDER BY atcl.id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Customer customer = (Customer)query.getSingleResult();
		return customer;
	}


}
