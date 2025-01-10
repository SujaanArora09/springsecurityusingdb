package com.training.springsecurity.service;

import java.util.List;

import com.training.springsecurity.entities.Customer;

public interface CustomerService {
	
	//Create
	public Customer addNewCustomer(Customer customer);
	//Retrieve
	public Customer getCustomer(Integer customerId);
	//Update
	public Customer updateCustomer(Customer customer);
	//Delete
	public String deleteCustomer(Integer customerId);
	
	//Retrieve All
	public List<Customer> getCustomers();


}
