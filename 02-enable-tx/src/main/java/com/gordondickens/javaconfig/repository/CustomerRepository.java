package com.gordondickens.javaconfig.repository;

import com.gordondickens.javaconfig.beans.Customer;
import org.springframework.dao.DataAccessException;

/**
 * @author Gordon Dickens (dickeg01)
 */
public interface CustomerRepository {

   public Customer findById(final Integer id) throws DataAccessException;

    public Customer save(final Customer customer) throws DataAccessException;
}
