package com.multichoice.crudDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multichoice.crudDemo.models.Customer;
import com.multichoice.crudDemo.service.CustomerService;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CrudDemoApplicationTests {
	
	@Autowired
	private CustomerService customerService; 

	@Test
	   public void getCustomerById() {
	   //   Mockito.when(customerService.getCustomerById("5f19e1a33439d135c003e87e")).thenReturn("Supriya");
	      Customer testName = customerService.getCustomerById("5f19e1a33439d135c003e87e");
	  //    Assert.assertEquals("Mock Product Name", testName);
	   }

}
