package com.demo.fashion.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderProduct.
 */
@Entity
@Table(name = "order_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Float price;

    @Column(name = "sale")
    private Float sale;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderProduct quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public OrderProduct price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSale() {
        return sale;
    }

    public OrderProduct sale(Float sale) {
        this.sale = sale;
        return this;
    }

    public void setSale(Float sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public OrderProduct product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public OrderProduct orders(Orders orders) {
        this.orders = orders;
        return this;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderProduct orderProduct = (OrderProduct) o;
        if (orderProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
            "id=" + getId() +
            ", quantity='" + getQuantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", sale='" + getSale() + "'" +
            "}";
    }
}
