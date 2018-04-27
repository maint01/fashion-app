package com.demo.fashion.service;

import com.demo.fashion.web.rest.vm.CartVM;

/**
 * @author bietdoicamtu.
 *         Created on 4/22/2018
 */
public interface CartService {
    Long saveCart(CartVM cartVM);
}
