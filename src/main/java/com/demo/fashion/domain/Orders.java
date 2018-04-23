package com.demo.fashion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

/**
 * A Orders.
 */
@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_created")
    private ZonedDateTime timeCreated;

    @Column(name = "status")
    private Integer status;

    @Column(name = "pay_category")
    private Integer payCategory;

    @Transient
    private List<CodPayment> lstCodPayment;

    @Transient
    private List<OrderProduct> lstOrderProduct;

    @ManyToOne
    private Customer customer;



    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CodPayment> codPayments = new HashSet<>();

    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTimeCreated() {
        return timeCreated;
    }

    public Orders timeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
        return this;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Integer getStatus() {
        return status;
    }

    public Orders status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayCategory() {
        return payCategory;
    }

    public Orders payCategory(Integer payCategory) {
        this.payCategory = payCategory;
        return this;
    }

    public void setPayCategory(Integer payCategory) {
        this.payCategory = payCategory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Orders customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<CodPayment> getCodPayments() {
        return codPayments;
    }

    public Orders codPayments(Set<CodPayment> codPayments) {
        this.codPayments = codPayments;
        return this;
    }

    public Orders addCodPayment(CodPayment codPayment) {
        this.codPayments.add(codPayment);
        codPayment.setOrders(this);
        return this;
    }

    public Orders removeCodPayment(CodPayment codPayment) {
        this.codPayments.remove(codPayment);
        codPayment.setOrders(null);
        return this;
    }

    public void setCodPayments(Set<CodPayment> codPayments) {
        this.codPayments = codPayments;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public Orders orderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        return this;
    }

    public Orders addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
        orderProduct.setOrders(this);
        return this;
    }

    public Orders removeOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.remove(orderProduct);
        orderProduct.setOrders(null);
        return this;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Orders orders = (Orders) o;
        if (orders.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orders.getId());
    }

    public List<CodPayment> getLstCodPayment() {
        return lstCodPayment;
    }

    public void setLstCodPayment(List<CodPayment> lstCodPayment) {
        this.lstCodPayment = lstCodPayment;
    }

    public List<OrderProduct> getLstOrderProduct() {
        return lstOrderProduct;
    }

    public void setLstOrderProduct(List<OrderProduct> lstOrderProduct) {
        this.lstOrderProduct = lstOrderProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Orders{" +
            "id=" + getId() +
            ", timeCreated='" + getTimeCreated() + "'" +
            ", status='" + getStatus() + "'" +
            ", payCategory='" + getPayCategory() + "'" +
            "}";
    }
}
