package com.example.customertracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.customertracker.model.Customer;
import com.example.customertracker.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomer(Model model) {
		List<Customer> customers = customerService.getCustomers();
		System.out.println(customers);
		model.addAttribute("customers", customers);
		return "list-customers";
	}
	@RequestMapping("/showFormForAdd")
	public String showFormForAddCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer,Model model) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdateCustomer(@RequestParam(name="customerId") int customerId,Model model) {
		Customer customer = customerService.getCustomer(customerId);
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam(name="customerId") int customerId) {
		customerService.deleteCustomer(customerId);
		return "redirect:/customer/list";
	}
}
