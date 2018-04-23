package com.demo.fashion.repository;

import com.demo.fashion.domain.OrderProduct;

import com.demo.fashion.domain.Orders;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrderProduct entity.
 */
@SuppressWarnings("unused")
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findByOrders(Orders orders);
}
