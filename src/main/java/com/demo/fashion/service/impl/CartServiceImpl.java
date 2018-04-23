package com.demo.fashion.service.impl;

import com.demo.fashion.domain.*;
import com.demo.fashion.repository.CodPaymentRepository;
import com.demo.fashion.repository.OrderProductRepository;
import com.demo.fashion.repository.OrdersRepository;
import com.demo.fashion.repository.ProductRepository;
import com.demo.fashion.service.CartService;
import com.demo.fashion.service.CustomerService;
import com.demo.fashion.service.util.CartConstants;
import com.demo.fashion.web.rest.vm.CartVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bietdoicamtu.
 *         Created on 4/22/2018
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

    private Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private CodPaymentRepository codPaymentRepository;


    @Override
    public boolean saveCart(CartVM cartVM) {
        log.info("Request to save cart.");
        //save customer
        Customer customer = cartVM.getCustomer();
        customer.setCustomerType(CartConstants.CUSTOMER_ANONYMOUS);
        customer = customerService.save(cartVM.getCustomer());
        //save orders
        Orders orders = new Orders()
            .timeCreated(ZonedDateTime.now())
            .status(CartConstants.NEW)
            .payCategory(CartConstants.POSTPAID)
            .customer(customer);
        orders = ordersRepository.save(orders);
        //save cod payment
        CodPayment codPayment = new CodPayment()
            .status(CartConstants.NEW)
            .shipDate(LocalDate.now().plusDays(CartConstants.ADD_DAY_DELIVERY))
            .orders(orders);
        codPaymentRepository.save(codPayment);
        //save order detail
        List<OrderProduct> lstOrderProduct = new ArrayList<>();
        List<Product> lstProduct = cartVM.getLstProduct();
        List<Integer> lstQuantity = cartVM.getLstQuantity();
        for (int i = 0; i < lstQuantity.size(); i++) {
            Product product = lstProduct.get(i);
            OrderProduct orderProduct = new OrderProduct()
                .orders(orders)
                .product(product)
                .quantity(lstQuantity.get(i))
                .price(product.getPrice())
                .sale(product.getCurrentSale());
            product.setQuantity(product.getQuantity() - lstQuantity.get(i));
            lstOrderProduct.add(orderProduct);
        }
        orderProductRepository.save(lstOrderProduct);
        //update quantity for product
        lstProduct = productRepository.save(lstProduct);

        return !lstProduct.isEmpty();
    }


}
