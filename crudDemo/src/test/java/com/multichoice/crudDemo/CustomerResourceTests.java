package com.multichoice.crudDemo;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multichoice.crudDemo.models.Customer;
import com.multichoice.crudDemo.service.CustomerService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerResourceTests extends CrudDemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired(required = true)
	CustomerService customerService;

	private MockMvc mockMvc;

	private Customer firstCustomer;
	private Customer secondCustomer;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		firstCustomer = new Customer();
		firstCustomer.setCustomerNumber("101");
		firstCustomer.setCustomerName("Pradeep");
		firstCustomer.setCustomerLocation("Kerala");
		firstCustomer.setCustomerMobileNumber("8989898989");
		firstCustomer.setStatus("true");

		secondCustomer = new Customer();
		secondCustomer.setCustomerNumber("102");
		secondCustomer.setCustomerName("Ravi");
		secondCustomer.setCustomerLocation("TamilNadu");
		secondCustomer.setCustomerMobileNumber("90909090909");
		secondCustomer.setStatus("true");

	}

	/**
	 * Test to create or insert new customer.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateCustomer() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/").content(asJsonString(firstCustomer))
				.contentType("application/json;charset=UTF-8").accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("Pradeep"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerMobileNumber").value("8989898989"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerLocation").value("Kerala"));

		mockMvc.perform(MockMvcRequestBuilders.put("/").content(asJsonString(secondCustomer))
				.contentType("application/json;charset=UTF-8").accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("Ravi"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerMobileNumber").value("90909090909"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerLocation").value("TamilNadu"));
	}

	/**
	 * Test to fetch all the customers from DB and validate the data based on the
	 * results obtained.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchAllCustomers() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$[0].customerNumber", is("101")))
				.andExpect(jsonPath("$[0].customerName", is("Pradeep")))
				.andExpect(jsonPath("$[0].customerLocation", is("Kerala")))
				.andExpect(jsonPath("$[0].customerMobileNumber", is("8989898989")))

				.andExpect(jsonPath("$[1].customerNumber", is("102")))
				.andExpect(jsonPath("$[1].customerName", is("Ravi")))
				.andExpect(jsonPath("$[1].customerLocation", is("TamilNadu")))
				.andExpect(jsonPath("$[1].customerMobileNumber", is("90909090909")));
	}

	/**
	 * Test to fetch the customer details and verify the details, based on the
	 * customer number provided.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchCustomerByNumber() throws Exception {
		mockMvc.perform(get("/101")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerNumber").value("101"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("Pradeep"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerMobileNumber").value("8989898989"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerLocation").value("Kerala"));
	}

	/**
	 * Test to delete an existing customer from the DB. Arguments : customerNumber
	 * Expected : Returns status code with 200, upon successfully deleting customer
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToRemoveValidCustomers() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/{customerNumber}", "102")).andExpect(status().isOk());

	}

	/**
	 * Test to verify if exception is thrown when attemting to delete a non-existing
	 * customer. Arguments passed : customerNumber Expected :
	 * CustomerNotFoundException <HttpStatus.NOT_FOUND>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToRemoveInvalidCustomers() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/{customerNumber}", "5f19e1a33439d135c003e87e"))
				.andExpect(status().isNotFound());

	}

	/**
	 * Method to conver the object to JSON String format
	 * 
	 * @param obj
	 * @return <String>
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
