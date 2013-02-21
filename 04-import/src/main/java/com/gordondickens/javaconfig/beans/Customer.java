package com.gordondickens.javaconfig.beans;

import org.springframework.stereotype.Component;

/**
 * @author Gordon Dickens (dickeg01)
 */
@Component
public class Customer extends AbstractBean {
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
