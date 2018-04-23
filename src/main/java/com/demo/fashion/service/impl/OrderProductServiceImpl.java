package com.demo.fashion.service.impl;

import com.demo.fashion.service.OrderProductService;
import com.demo.fashion.domain.OrderProduct;
import com.demo.fashion.repository.OrderProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing OrderProduct.
 */
@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService{

    private final Logger log = LoggerFactory.getLogger(OrderProductServiceImpl.class);
    
    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    /**
     * Save a orderProduct.
     *
     * @param orderProduct the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        log.debug("Request to save OrderProduct : {}", orderProduct);
        OrderProduct result = orderProductRepository.save(orderProduct);
        return result;
    }

    /**
     *  Get all the orderProducts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderProduct> findAll(Pageable pageable) {
        log.debug("Request to get all OrderProducts");
        Page<OrderProduct> result = orderProductRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one orderProduct by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderProduct findOne(Long id) {
        log.debug("Request to get OrderProduct : {}", id);
        OrderProduct orderProduct = orderProductRepository.findOne(id);
        return orderProduct;
    }

    /**
     *  Delete the  orderProduct by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderProduct : {}", id);
        orderProductRepository.delete(id);
    }
}
