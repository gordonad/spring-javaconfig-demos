package com.gordondickens.javaconfig;

import com.gordondickens.javaconfig.beans.Product;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * 
 * @author Gordon Dickens
 * @see org.springframework.context.annotation.ClassPathBeanDefinitionScanner
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory
 * @see org.springframework.beans.factory.support.BeanDefinitionReader
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
 * @see org.springframework.beans.factory.annotation.InjectionMetadata
 * @see org.springframework.beans.CachedIntrospectionResults
 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 * @see 
 */
@Configuration
@ComponentScan
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    
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
    
    
    @PostConstruct
    public void logBeans() {
        logger.debug(StringUtils.repeat('*',78));
        for (String bean : applicationContext.getBeanDefinitionNames()) {
            logger.debug("BEAN '{}'", bean);
        }
        logger.debug(StringUtils.repeat('*',78));
        logger.debug("ENV: '{}'",environment.toString());
        logger.debug(StringUtils.repeat('*',78));
    }
}
