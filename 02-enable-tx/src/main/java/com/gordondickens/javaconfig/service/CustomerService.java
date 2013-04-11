package com.gordondickens.javaconfig.service;

import com.gordondickens.javaconfig.beans.Customer;

/**
 * @author Gordon Dickens
 */
public interface CustomerService {
    public Customer save(final Customer customer);

    public Customer findById(final Integer id);
}
