package com.demo.fashion.service;

import com.demo.fashion.domain.PrePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing PrePayment.
 */
public interface PrePaymentService {

    /**
     * Save a prePayment.
     *
     * @param prePayment the entity to save
     * @return the persisted entity
     */
    PrePayment save(PrePayment prePayment);

    /**
     *  Get all the prePayments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PrePayment> findAll(Pageable pageable);

    /**
     *  Get the "id" prePayment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PrePayment findOne(Long id);

    /**
     *  Delete the "id" prePayment.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
