package com.multichoice.crudDemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multichoice.crudDemo.service.CustomerService;
import com.multichoice.crudDemo.models.Customer;

@RestController
@RequestMapping(value = "/")
public class CustomerController {

	private final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired(required = true)
	CustomerService customerService;

	/**
	 * Method to create customer
	 * 
	 * @param customer
	 * @return the added customer details
	 */
	@PutMapping(value = "/", produces = { "application/json" })
	public Customer addNewCustomer(@RequestBody Customer customer) {
		LOG.info("Saving Customer.");
		return customerService.insertCustomer(customer);
	}

	/**
	 * Method to update customer data
	 * 
	 * @param customer
	 * @return Success or Failure message
	 */

	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String updateCustomer(@RequestBody Customer customer) {
		LOG.info("Update Customer.");
		return customerService.updateCustomer(customer);
	}

	/**
	 * Method to delete a customer data
	 * 
	 * @param customer
	 * @return Success or failure message
	 */
	@DeleteMapping(value = "/{customerNumber}", produces = "application/json")
	public String deleteCustomer(@PathVariable String customerNumber) {
		LOG.info("Delete  Customer." + customerNumber);
		return customerService.deleteCustomer(customerNumber);
	}

	/**
	 * Method to fetch a specific customer details.
	 * 
	 * @param userId
	 * @return Customer details
	 */
	@GetMapping(value = "/{customerNumber}", produces = "application/json")
	public Customer getCustomer(@PathVariable String customerNumber) {
		LOG.info("Getting customer with ID: {}.", customerNumber);
		return customerService.getCustomerById(customerNumber);
	}

	/**
	 * Method to fetch all the customer details.
	 * 
	 * @return List<Customer>
	 */
	@GetMapping(value = "/", produces = "application/json")
	public List<Customer> getAllCustomers() {
		LOG.info("Getting all users.");
		return customerService.getAllCustomers();
	}

}
