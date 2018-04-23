package com.demo.fashion.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A CodPayment.
 */
@Entity
@Table(name = "cod_payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CodPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ship_date")
    private LocalDate shipDate;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    private Orders orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getShipDate() {
        return shipDate;
    }

    public CodPayment shipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public void setShipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
    }

    public Integer getStatus() {
        return status;
    }

    public CodPayment status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Orders getOrders() {
        return orders;
    }

    public CodPayment orders(Orders orders) {
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
        CodPayment codPayment = (CodPayment) o;
        if (codPayment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), codPayment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CodPayment{" +
            "id=" + getId() +
            ", shipDate='" + getShipDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
