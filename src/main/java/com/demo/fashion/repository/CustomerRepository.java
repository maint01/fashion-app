package com.demo.fashion.repository;

import com.demo.fashion.domain.Customer;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@SuppressWarnings("unused")
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findOneByUsername(String username);

    Optional<Customer> findOneByEmailAndUsernameIsNotNull(String email);
}
