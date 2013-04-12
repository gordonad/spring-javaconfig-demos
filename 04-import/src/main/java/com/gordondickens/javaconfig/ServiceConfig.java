package com.gordondickens.javaconfig;

import com.gordondickens.javaconfig.beans.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Gordon Dickens
 * @see
 */
@Configuration
@ComponentScan
public class ServiceConfig {
    private static final Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @Bean
    public Product product() {
        Product product = new Product();
        product.setProductName("swizzles");
        return product;
    }
}
