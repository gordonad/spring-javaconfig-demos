package com.gordondickens.javaconfig.beans;

import org.springframework.stereotype.Component;

/**
 * @author Gordon Dickens
 */
@Component
public class Account extends AbstractBean{
    private String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
