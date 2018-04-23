package com.demo.fashion.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PrePayment.
 */
@Entity
@Table(name = "pre_payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrePayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "time_payment")
    private ZonedDateTime timePayment;

    @ManyToOne
    private Bank bank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public PrePayment status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ZonedDateTime getTimePayment() {
        return timePayment;
    }

    public PrePayment timePayment(ZonedDateTime timePayment) {
        this.timePayment = timePayment;
        return this;
    }

    public void setTimePayment(ZonedDateTime timePayment) {
        this.timePayment = timePayment;
    }

    public Bank getBank() {
        return bank;
    }

    public PrePayment bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrePayment prePayment = (PrePayment) o;
        if (prePayment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prePayment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrePayment{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", timePayment='" + getTimePayment() + "'" +
            "}";
    }
}
