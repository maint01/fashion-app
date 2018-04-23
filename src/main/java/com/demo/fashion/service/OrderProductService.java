package com.demo.fashion.service;

import com.demo.fashion.domain.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing OrderProduct.
 */
public interface OrderProductService {

    /**
     * Save a orderProduct.
     *
     * @param orderProduct the entity to save
     * @return the persisted entity
     */
    OrderProduct save(OrderProduct orderProduct);

    /**
     *  Get all the orderProducts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OrderProduct> findAll(Pageable pageable);

    /**
     *  Get the "id" orderProduct.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OrderProduct findOne(Long id);

    /**
     *  Delete the "id" orderProduct.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
