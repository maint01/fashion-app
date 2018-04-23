package com.demo.fashion.service;

import com.demo.fashion.domain.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Bank.
 */
public interface BankService {

    /**
     * Save a bank.
     *
     * @param bank the entity to save
     * @return the persisted entity
     */
    Bank save(Bank bank);

    /**
     *  Get all the banks.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Bank> findAll(Pageable pageable);

    /**
     *  Get the "id" bank.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Bank findOne(Long id);

    /**
     *  Delete the "id" bank.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
