package com.multichoice.crudDemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.multichoice.crudDemo.exceptions.CustomerNotFoundException;
import com.multichoice.crudDemo.exceptions.UnauthorizedException;
import com.multichoice.crudDemo.models.Customer;
import com.multichoice.crudDemo.utils.ConstantValues;

@Service
@CacheConfig(cacheNames = { "customer" })
public class CustomerServiceImpl implements CustomerService {

	private final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Customer insertCustomer(Customer customer) {
		mongoTemplate.save(customer);
		return customer;
	}

	@Override
	public String updateCustomer(Customer customer) {
		String message = null;
		Customer customerID = mongoTemplate.findById(customer.getCustomerNumber(), Customer.class);
		if (customerID != null) {
			LOG.info("Customer Exists...Proceeding with Update....");
			mongoTemplate.save(customer);
			message = "Successfully Updated Data";
		} else {
			message = "Specified Customer Number does not exists";

		}
		return message;
	}

	@Cacheable
	@Override
	public List<Customer> getAllCustomers() {
		LOG.info("Calling getAllCustomers() of IMPL class");
		return mongoTemplate.findAll(Customer.class);
	}

	@Override
	public Customer getCustomerById(String customerNumber) {
		Customer customer = mongoTemplate.findById(customerNumber, Customer.class);
		if ((customer != null) && (customer.getStatus() != null && customer.getStatus().equals(ConstantValues.FALSE))) {
			LOG.info("Customer with status - FALSE");
			throw new UnauthorizedException("Not a Valid Customer");
		}
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer Record, does not exists");
		}
		
		return customer;
	}

	@Override
	public String deleteCustomer(String customerNumber) {
		String deletedMessage = null;
		Customer customer = mongoTemplate.findById(customerNumber, Customer.class);
		LOG.info("deleteCustomer  customer ::: " + customer);
		if (customer != null) {
			mongoTemplate.remove(customer);
			deletedMessage = "Successfully removed the Customer Record";
		} else {
			throw new CustomerNotFoundException("Failed to  removed the Customer Record, as customer does not exists");
		}
		return deletedMessage;
	}

}
