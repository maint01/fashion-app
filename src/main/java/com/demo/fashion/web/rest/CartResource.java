package com.demo.fashion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.fashion.service.CartService;
import com.demo.fashion.web.rest.vm.CartVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bietdoicamtu.
 *         Created on 4/22/2018
 */
@RestController
@RequestMapping("/api")
public class CartResource {
    private Logger log = LoggerFactory.getLogger(CartResource.class);

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/save-cart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Object> saveCart(@RequestBody CartVM cartVM) {
        log.info("REST request to save cart: {}", cartVM);
        try {
            Long codeOrder = cartService.saveCart(cartVM);
            if(codeOrder != null){
                Map<String, Object> maps = new HashMap<>();
                maps.put("codeOrder", codeOrder);
                return new ResponseEntity<>(maps, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
