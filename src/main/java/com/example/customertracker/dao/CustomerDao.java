package com.example.customertracker.dao;

import java.util.List;

import com.example.customertracker.model.Customer;

public interface CustomerDao {
	public List<Customer> listCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);
}
