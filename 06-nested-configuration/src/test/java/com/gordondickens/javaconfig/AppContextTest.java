package com.gordondickens.javaconfig;

import com.gordondickens.javaconfig.beans.Account;
import com.gordondickens.javaconfig.beans.Customer;
import com.gordondickens.javaconfig.beans.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Gordon Dickens
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppContextTest {
    
    @Autowired
    Customer customer;
    
    @Autowired
    Account account;
    
    @Autowired
    Product product;
    
    @Test
    public void verifyBeans() {
        assertThat(customer, notNullValue());
        assertThat(account, notNullValue());
        assertThat(product, notNullValue());
    }
}
