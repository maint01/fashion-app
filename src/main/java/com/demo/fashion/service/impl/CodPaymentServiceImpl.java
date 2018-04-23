package com.demo.fashion.service.impl;

import com.demo.fashion.service.CodPaymentService;
import com.demo.fashion.domain.CodPayment;
import com.demo.fashion.repository.CodPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing CodPayment.
 */
@Service
@Transactional
public class CodPaymentServiceImpl implements CodPaymentService{

    private final Logger log = LoggerFactory.getLogger(CodPaymentServiceImpl.class);
    
    private final CodPaymentRepository codPaymentRepository;

    public CodPaymentServiceImpl(CodPaymentRepository codPaymentRepository) {
        this.codPaymentRepository = codPaymentRepository;
    }

    /**
     * Save a codPayment.
     *
     * @param codPayment the entity to save
     * @return the persisted entity
     */
    @Override
    public CodPayment save(CodPayment codPayment) {
        log.debug("Request to save CodPayment : {}", codPayment);
        CodPayment result = codPaymentRepository.save(codPayment);
        return result;
    }

    /**
     *  Get all the codPayments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CodPayment> findAll(Pageable pageable) {
        log.debug("Request to get all CodPayments");
        Page<CodPayment> result = codPaymentRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one codPayment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CodPayment findOne(Long id) {
        log.debug("Request to get CodPayment : {}", id);
        CodPayment codPayment = codPaymentRepository.findOne(id);
        return codPayment;
    }

    /**
     *  Delete the  codPayment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CodPayment : {}", id);
        codPaymentRepository.delete(id);
    }
}
