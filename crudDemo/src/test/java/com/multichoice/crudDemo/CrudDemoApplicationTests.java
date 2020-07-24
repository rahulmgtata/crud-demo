package com.multichoice.crudDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CrudDemoApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
//	private final Logger LOG = LoggerFactory.getLogger(CrudDemoApplicationTests.class);
//	
//	@Autowired
//	private CustomerService customerService; 
//
//	@Test
//	   public void getCustomerById() {
//		String expectedCustomerName = "Sagar";
//	      Customer customer = customerService.getCustomerById("5f19e0083439d13bd4b57670");
//	      LOG.info("customer :: "+customer);
//	      LOG.info("customer :: "+customer.getCustomerName());
//	      Assert.assertEquals(expectedCustomerName, customer.getCustomerName());
//	   }

}
