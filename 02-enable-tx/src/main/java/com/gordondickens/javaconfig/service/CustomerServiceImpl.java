package com.gordondickens.javaconfig.service;

import com.gordondickens.javaconfig.beans.Customer;
import com.gordondickens.javaconfig.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gordon Dickens (dickeg01)
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Override
    public Customer save(final Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(final Integer id) {
        return customerRepository.findById(id);
    }
}
