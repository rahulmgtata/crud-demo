package com.multichoice.crudDemo.service;

import java.util.List;
import com.multichoice.crudDemo.models.Customer;

public interface CustomerService {

	Customer insertCustomer(Customer customer);

	String updateCustomer(Customer customer);

	List<Customer> getAllCustomers();

	Customer getCustomerById(String customerId);

	String deleteCustomer(String customerNumber);

}
