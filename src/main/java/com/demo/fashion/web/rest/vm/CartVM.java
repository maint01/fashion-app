package com.demo.fashion.web.rest.vm;

import com.demo.fashion.domain.Customer;
import com.demo.fashion.domain.Product;

import java.util.List;

/**
 * @author bietdoicamtu.
 *         Created on 4/22/2018
 */
public class CartVM {
    private List<Product> lstProduct;
    private List<Integer> lstQuantity;
    private Customer customer;

    public List<Product> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<Product> lstProduct) {
        this.lstProduct = lstProduct;
    }

    public List<Integer> getLstQuantity() {
        return lstQuantity;
    }

    public void setLstQuantity(List<Integer> lstQuantity) {
        this.lstQuantity = lstQuantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
