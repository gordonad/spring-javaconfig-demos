package com.gordondickens.javaconfig;

import com.gordondickens.javaconfig.beans.Customer;
import com.gordondickens.javaconfig.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

/**
 * @author Gordon Dickens
 * @see org.springframework.test.context.ContextLoaderUtils
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppContextTest {
    
    @Autowired
    CustomerService customerService;
    
    @Test
    public void verifyBeans() {
        assertThat(customerService, notNullValue());
    }
    
    @Test
    public void verifyFindById() {
        Customer customer = customerService.findById(1);
        assertThat(customer, notNullValue());
        assertThat(customer.getCustomerName(), startsWith("ABC"));
    }
    
}
