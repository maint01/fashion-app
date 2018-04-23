package com.demo.fashion.service.impl;

import com.demo.fashion.service.PrePaymentService;
import com.demo.fashion.domain.PrePayment;
import com.demo.fashion.repository.PrePaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing PrePayment.
 */
@Service
@Transactional
public class PrePaymentServiceImpl implements PrePaymentService{

    private final Logger log = LoggerFactory.getLogger(PrePaymentServiceImpl.class);
    
    private final PrePaymentRepository prePaymentRepository;

    public PrePaymentServiceImpl(PrePaymentRepository prePaymentRepository) {
        this.prePaymentRepository = prePaymentRepository;
    }

    /**
     * Save a prePayment.
     *
     * @param prePayment the entity to save
     * @return the persisted entity
     */
    @Override
    public PrePayment save(PrePayment prePayment) {
        log.debug("Request to save PrePayment : {}", prePayment);
        PrePayment result = prePaymentRepository.save(prePayment);
        return result;
    }

    /**
     *  Get all the prePayments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PrePayment> findAll(Pageable pageable) {
        log.debug("Request to get all PrePayments");
        Page<PrePayment> result = prePaymentRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one prePayment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PrePayment findOne(Long id) {
        log.debug("Request to get PrePayment : {}", id);
        PrePayment prePayment = prePaymentRepository.findOne(id);
        return prePayment;
    }

    /**
     *  Delete the  prePayment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PrePayment : {}", id);
        prePaymentRepository.delete(id);
    }
}
