package com.demo.fashion.service.impl;

import com.demo.fashion.security.AuthoritiesConstants;
import com.demo.fashion.security.SecurityUtils;
import com.demo.fashion.service.CustomerService;
import com.demo.fashion.domain.Customer;
import com.demo.fashion.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginContext;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Customer.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Save a customer.
     *
     * @param customer the entity to save
     * @return the persisted entity
     */
    @Override
    public Customer save(Customer customer) {
        log.debug("Request to save Customer : {}", customer);
        if (customer.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }
        if (customer.getCustomerType() == null) {
            customer.customerType(AuthoritiesConstants.WEBSITE);
        }
        Customer result = customerRepository.save(customer);
        return result;
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        Page<Customer> result = customerRepository.findAll(pageable);
        return result;
    }

    /**
     * Get one customer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Customer findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        Customer customer = customerRepository.findOne(id);
        return customer;
    }

    /**
     * Delete the  customer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.delete(id);
    }

    @Override
    public Authentication authenticate(String username, String password) throws AuthenticationException {
        Optional<Customer> optional = customerRepository.findOneByUsername(username);
        return optional.map(customer -> {
            boolean checkPassword = passwordEncoder.matches(password, customer.getPassword());
            if (checkPassword) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customer.getCustomerType()));
                return new JaasAuthenticationToken(username, password, authorities, null);
            } else {
                return null;
            }
        }).orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the " +
            "database"));
    }

    @Override
    public Boolean changePassword(String newPassword) {
        String username = SecurityUtils.getCurrentUserLogin();
        return customerRepository.findOneByUsername(username).map(customer -> {
            customer.setPassword(passwordEncoder.encode(newPassword));
            customer = customerRepository.save(customer);
            return customer != null;
        }).orElse(false);
    }

    @Override
    public Optional<Customer> findOneByUsername(String username) {
        log.debug("Request to find Customer by username: {}", username);
        return customerRepository.findOneByUsername(username);
    }


    @Override
    public Optional<Customer> findOneByEmailAndUsernameIsNotNull(String email) {
        log.debug("Request to find Customer by email and username is not null : {}", email);
        return customerRepository.findOneByEmailAndUsernameIsNotNull(email);
    }

}
