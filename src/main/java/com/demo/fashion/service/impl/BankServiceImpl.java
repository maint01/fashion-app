package com.demo.fashion.service.impl;

import com.demo.fashion.service.BankService;
import com.demo.fashion.domain.Bank;
import com.demo.fashion.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Bank.
 */
@Service
@Transactional
public class BankServiceImpl implements BankService{

    private final Logger log = LoggerFactory.getLogger(BankServiceImpl.class);
    
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    /**
     * Save a bank.
     *
     * @param bank the entity to save
     * @return the persisted entity
     */
    @Override
    public Bank save(Bank bank) {
        log.debug("Request to save Bank : {}", bank);
        Bank result = bankRepository.save(bank);
        return result;
    }

    /**
     *  Get all the banks.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bank> findAll(Pageable pageable) {
        log.debug("Request to get all Banks");
        Page<Bank> result = bankRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one bank by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Bank findOne(Long id) {
        log.debug("Request to get Bank : {}", id);
        Bank bank = bankRepository.findOne(id);
        return bank;
    }

    /**
     *  Delete the  bank by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bank : {}", id);
        bankRepository.delete(id);
    }
}
