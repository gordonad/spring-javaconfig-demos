package com.gordondickens.javaconfig.beans;

/**
 * @author Gordon Dickens
 */
public class Product extends AbstractBean {
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
