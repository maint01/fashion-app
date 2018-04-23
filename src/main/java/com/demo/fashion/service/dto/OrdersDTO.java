package com.demo.fashion.service.dto;

import com.demo.fashion.domain.CodPayment;
import com.demo.fashion.domain.Customer;
import com.demo.fashion.domain.OrderProduct;
import sun.rmi.runtime.Log;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author bietdoicamtu.
 *         Created on 4/22/2018
 */
public class OrdersDTO {
    private Long id;
    private ZonedDateTime timeCreated;
    private Integer status;
    private Integer payCategory;
    private List<CodPayment> lstCodPayment;
    private List<OrderProduct> lstOrderProduct;
    private Customer customer;

    public Long getId() {
        return id;
    }

    public ZonedDateTime getTimeCreated() {
        return timeCreated;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getPayCategory() {
        return payCategory;
    }

    public List<CodPayment> getLstCodPayment() {
        return lstCodPayment;
    }

    public List<OrderProduct> getLstOrderProduct() {
        return lstOrderProduct;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPayCategory(Integer payCategory) {
        this.payCategory = payCategory;
    }

    public void setLstCodPayment(List<CodPayment> lstCodPayment) {
        this.lstCodPayment = lstCodPayment;
    }

    public void setLstOrderProduct(List<OrderProduct> lstOrderProduct) {
        this.lstOrderProduct = lstOrderProduct;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrdersDTO id(Long id) {
        this.id = id;
        return this;
    }

    public OrdersDTO timeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
        return this;
    }

    public OrdersDTO status(Integer status) {
        this.status = status;
        return this;
    }

    public OrdersDTO payCategory(Integer payCategory) {
        this.payCategory = payCategory;
        return this;
    }

    public OrdersDTO lstCodPayment(List<CodPayment> lstCodPayment) {
        lstCodPayment.forEach(i->{
            i.setOrders(null);
        });
        this.lstCodPayment = lstCodPayment;
        return this;
    }

    public OrdersDTO lstOrderProduct(List<OrderProduct> lstOrderProduct) {
        lstOrderProduct.forEach(i->{
            i.setOrders(null);
        });
        this.lstOrderProduct = lstOrderProduct;
        return this;
    }

    public OrdersDTO customer(Customer customer) {
        this.customer = customer;
        return this;
    }
}
