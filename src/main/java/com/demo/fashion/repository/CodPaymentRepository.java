package com.demo.fashion.repository;

import com.demo.fashion.domain.CodPayment;

import com.demo.fashion.domain.Orders;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CodPayment entity.
 */
@SuppressWarnings("unused")
public interface CodPaymentRepository extends JpaRepository<CodPayment,Long> {
    List<CodPayment> findByOrders(Orders orders);
}
