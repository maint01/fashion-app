package com.demo.fashion.service.impl;

import com.demo.fashion.repository.CodPaymentRepository;
import com.demo.fashion.repository.OrderProductRepository;
import com.demo.fashion.service.CodPaymentService;
import com.demo.fashion.service.OrderProductService;
import com.demo.fashion.service.OrdersService;
import com.demo.fashion.domain.Orders;
import com.demo.fashion.repository.OrdersRepository;
import com.demo.fashion.service.dto.OrdersDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Orders.
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    private final Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);

    private final OrdersRepository ordersRepository;

    @Autowired
    private CodPaymentRepository codPaymentRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    /**
     * Save a orders.
     *
     * @param orders the entity to save
     * @return the persisted entity
     */
    @Override
    public Orders save(Orders orders) {
        log.debug("Request to save Orders : {}", orders);
        Orders result = ordersRepository.save(orders);
        return result;
    }

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Orders> findAll(Pageable pageable) {
        log.debug("Request to get all Orders");
        Page<Orders> result = ordersRepository.findAll(pageable);
        return result;
    }

    /**
     * Get one orders by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Orders findOne(Long id) {
        log.debug("Request to get Orders : {}", id);
        Orders orders = ordersRepository.findOne(id);
        return orders;
    }

    /**
     * Delete the  orders by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Orders : {}", id);
        ordersRepository.delete(id);
    }

    @Override
    public OrdersDTO searchOrder(String codeOrder) {
        log.debug("Request to search Orders : {}", codeOrder);
        if (StringUtils.isNumeric(codeOrder)) {
            Orders orders = ordersRepository.findOne(Long.parseLong(codeOrder));
            if (orders != null) {
                orders.setLstCodPayment(codPaymentRepository.findByOrders(orders));
                orders.setLstOrderProduct(orderProductRepository.findByOrders(orders));
                return this.toDTO(orders);
            }else{
                return null;
            }
        } else {
            return null;
        }
    }

    private OrdersDTO toDTO(Orders orders){
        return new OrdersDTO()
            .id(orders.getId())
            .payCategory(orders.getPayCategory())
            .timeCreated(orders.getTimeCreated())
            .status(orders.getStatus())
            .customer(orders.getCustomer())
            .lstCodPayment(orders.getLstCodPayment())
            .lstOrderProduct(orders.getLstOrderProduct());
    }
}
