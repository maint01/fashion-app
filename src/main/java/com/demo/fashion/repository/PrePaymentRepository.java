package com.demo.fashion.repository;

import com.demo.fashion.domain.PrePayment;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PrePayment entity.
 */
@SuppressWarnings("unused")
public interface PrePaymentRepository extends JpaRepository<PrePayment,Long> {

}
