package com.demo.fashion.service;

import com.demo.fashion.domain.Customer;
import com.demo.fashion.domain.Orders;
import com.demo.fashion.service.dto.OrdersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Orders.
 */
public interface OrdersService {

    /**
     * Save a orders.
     *
     * @param orders the entity to save
     * @return the persisted entity
     */
    Orders save(Orders orders);

    /**
     *  Get all the orders.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Orders> findAll(Pageable pageable);

    /**
     *  Get the "id" orders.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Orders findOne(Long id);

    /**
     *  Delete the "id" orders.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    OrdersDTO searchOrder(String codeOrder);

    List<OrdersDTO> getOrdersByCustomer(Customer customer);
}
