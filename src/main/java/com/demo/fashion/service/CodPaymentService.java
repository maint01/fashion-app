package com.demo.fashion.service;

import com.demo.fashion.domain.CodPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing CodPayment.
 */
public interface CodPaymentService {

    /**
     * Save a codPayment.
     *
     * @param codPayment the entity to save
     * @return the persisted entity
     */
    CodPayment save(CodPayment codPayment);

    /**
     *  Get all the codPayments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CodPayment> findAll(Pageable pageable);

    /**
     *  Get the "id" codPayment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CodPayment findOne(Long id);

    /**
     *  Delete the "id" codPayment.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
