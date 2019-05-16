package com.example.customertracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.customertracker.dao.CustomerDao;
import com.example.customertracker.model.Customer;
@Service
public class CustomerserviceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Transactional
	public List<Customer> getCustomers() {
		return customerDao.listCustomers();
	}
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}
	@Transactional
	public Customer getCustomer(int customerId) {
		return customerDao.getCustomer(customerId);
	}
	@Transactional
	public void deleteCustomer(int customerId) {
		customerDao.deleteCustomer(customerId);
	}

}
