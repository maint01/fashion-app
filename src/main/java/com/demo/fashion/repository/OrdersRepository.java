package com.demo.fashion.repository;

import com.demo.fashion.domain.Customer;
import com.demo.fashion.domain.Orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Orders entity.
 */
@SuppressWarnings("unused")
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    Page<Orders> findByCustomer(Customer customer, Pageable pageable);
}
