package com.multichoice.crudDemo.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.multichoice.crudDemo.models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	
}