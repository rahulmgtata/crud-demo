package com.multichoice.crudDemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class CustomerResourceTests extends CrudDemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test to fetch the customer details and verify the details, based on the
	 * customer number provided.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchCustomerByNumber() throws Exception {
		mockMvc.perform(get("/5f19e1a33439d135c003e87e")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.customerName").value("Supriya"))
				.andExpect(jsonPath("$.customerLocation").value("Gujarat"))
				.andExpect(jsonPath("$.customerMobileNumber").value("42424242234"));

	}

	/**
	 * Test to delete an existing customer from the DB. Arguments : customerNumber
	 * Expected : Returns status code with 200, upon successfully deleting customer
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToDeleteValidCustomers() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/{customerNumber}", "5f19e1a33439d135c003e87e"))
				.andExpect(status().isOk());

	}

	/**
	 * Test to verify if exception is thrown when attemting to delete a non-existing
	 * customer. Arguments passed : customernumber Expected :
	 * CustomerNotFoundException <HttpStatus.NOT_FOUND>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToDeleteInvalidCustomers() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/{customerNumber}", "5f19e1a33439d135c003e87e"))
				.andExpect(status().isNotFound());

	}

}
