package com.gordondickens.javaconfig.repository;

import com.gordondickens.javaconfig.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gordon Dickens (dickeg01)
 */
@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    DataSource dataSource;

    private SimpleJdbcInsert insertCustomer;

    @PostConstruct
    public void init() {
        this.insertCustomer = new SimpleJdbcInsert(dataSource)
                .withTableName("customer")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public Customer findById(final Integer id) throws DataAccessException {
        Customer customer;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            customer = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, customer_name FROM customer WHERE id=:id",
                    params,
                    new JdbcCustomerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Customer.class, id);
        }
        return customer;
    }

    @Override
    public Customer save(final Customer customer) throws DataAccessException {
        Number newKey = this.insertCustomer.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("id", customer.getId())
                        .addValue("customer_name", customer.getCustomerName()));
        customer.setId(newKey.intValue());
        return customer;
    }

    private class JdbcCustomerRowMapper implements ParameterizedRowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rownum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setCustomerName(rs.getString("customer_name"));
            return customer;
        }
    }

}
