package com.gordondickens.javaconfig;

import com.gordondickens.javaconfig.beans.Account;
import com.gordondickens.javaconfig.beans.Customer;
import com.gordondickens.javaconfig.beans.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Gordon Dickens
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppContextTest {
    private static final Logger logger = LoggerFactory.getLogger(AppContextTest.class);

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
        assertThat("Product Name MUST be at least 1 character", product.getProductName().length(), greaterThan(1));
        assertThat("Product Name MUST match", product.getProductName(), equalToIgnoringCase("swizzles"));

        logger.debug("Product {}", product.toString());

    }
}
