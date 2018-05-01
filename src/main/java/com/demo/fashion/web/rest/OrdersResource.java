package com.demo.fashion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.fashion.domain.Orders;
import com.demo.fashion.security.SecurityUtils;
import com.demo.fashion.service.CustomerService;
import com.demo.fashion.service.OrdersService;
import com.demo.fashion.service.dto.OrdersDTO;
import com.demo.fashion.web.rest.util.HeaderUtil;
import com.demo.fashion.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Orders.
 */
@RestController
@RequestMapping("/api")
public class OrdersResource {

    private final Logger log = LoggerFactory.getLogger(OrdersResource.class);

    private static final String ENTITY_NAME = "orders";

    private final OrdersService ordersService;

    @Autowired
    private CustomerService customerService;

    public OrdersResource(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * POST  /orders : Create a new orders.
     *
     * @param orders the orders to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orders, or with status 400 (Bad Request) if the orders has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/orders")
    @Timed
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) throws URISyntaxException {
        log.debug("REST request to save Orders : {}", orders);
        if (orders.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new orders cannot already have an ID")).body(null);
        }
        Orders result = ordersService.save(orders);
        return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orders : Updates an existing orders.
     *
     * @param orders the orders to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orders,
     * or with status 400 (Bad Request) if the orders is not valid,
     * or with status 500 (Internal Server Error) if the orders couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/orders")
    @Timed
    public ResponseEntity<Orders> updateOrders(@RequestBody Orders orders) throws URISyntaxException {
        log.debug("REST request to update Orders : {}", orders);
        if (orders.getId() == null) {
            return createOrders(orders);
        }
        Orders result = ordersService.save(orders);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orders.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orders : get all the orders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orders in body
     */
    @GetMapping("/orders")
    @Timed
    public ResponseEntity<List<Orders>> getAllOrders(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Orders");
        Page<Orders> page = ordersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /orders/:id : get the "id" orders.
     *
     * @param id the id of the orders to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orders, or with status 404 (Not Found)
     */
    @GetMapping("/orders/{id}")
    @Timed
    public ResponseEntity<Orders> getOrders(@PathVariable Long id) {
        log.debug("REST request to get Orders : {}", id);
        Orders orders = ordersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    @RequestMapping(value = "/search-order/{codeOrder}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrdersDTO> searchOrders(@PathVariable String codeOrder) {
        log.debug("REST request to search Orders : {}", codeOrder);
        if (StringUtils.isNumeric(codeOrder)) {
            OrdersDTO orders = ordersService.searchOrder(codeOrder);
            if (orders != null) {
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * DELETE  /orders/:id : delete the "id" orders.
     *
     * @param id the id of the orders to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/orders/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrders(@PathVariable Long id) {
        log.debug("REST request to delete Orders : {}", id);
        ordersService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @RequestMapping(value = "/site/get-ordered",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity getOrdersForCustomer() {
        log.debug("REST request to get list Ordered");
        String username = SecurityUtils.getCurrentUserLogin();
        return customerService.findOneByUsername(username)
            .map(customer -> new ResponseEntity<>(ordersService.getOrdersByCustomer(customer), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
