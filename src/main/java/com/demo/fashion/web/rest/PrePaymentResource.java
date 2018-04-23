package com.demo.fashion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.fashion.domain.PrePayment;
import com.demo.fashion.service.PrePaymentService;
import com.demo.fashion.web.rest.util.HeaderUtil;
import com.demo.fashion.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PrePayment.
 */
@RestController
@RequestMapping("/api")
public class PrePaymentResource {

    private final Logger log = LoggerFactory.getLogger(PrePaymentResource.class);

    private static final String ENTITY_NAME = "prePayment";
        
    private final PrePaymentService prePaymentService;

    public PrePaymentResource(PrePaymentService prePaymentService) {
        this.prePaymentService = prePaymentService;
    }

    /**
     * POST  /pre-payments : Create a new prePayment.
     *
     * @param prePayment the prePayment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prePayment, or with status 400 (Bad Request) if the prePayment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pre-payments")
    @Timed
    public ResponseEntity<PrePayment> createPrePayment(@RequestBody PrePayment prePayment) throws URISyntaxException {
        log.debug("REST request to save PrePayment : {}", prePayment);
        if (prePayment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new prePayment cannot already have an ID")).body(null);
        }
        PrePayment result = prePaymentService.save(prePayment);
        return ResponseEntity.created(new URI("/api/pre-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pre-payments : Updates an existing prePayment.
     *
     * @param prePayment the prePayment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prePayment,
     * or with status 400 (Bad Request) if the prePayment is not valid,
     * or with status 500 (Internal Server Error) if the prePayment couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pre-payments")
    @Timed
    public ResponseEntity<PrePayment> updatePrePayment(@RequestBody PrePayment prePayment) throws URISyntaxException {
        log.debug("REST request to update PrePayment : {}", prePayment);
        if (prePayment.getId() == null) {
            return createPrePayment(prePayment);
        }
        PrePayment result = prePaymentService.save(prePayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prePayment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pre-payments : get all the prePayments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prePayments in body
     */
    @GetMapping("/pre-payments")
    @Timed
    public ResponseEntity<List<PrePayment>> getAllPrePayments(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of PrePayments");
        Page<PrePayment> page = prePaymentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pre-payments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pre-payments/:id : get the "id" prePayment.
     *
     * @param id the id of the prePayment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prePayment, or with status 404 (Not Found)
     */
    @GetMapping("/pre-payments/{id}")
    @Timed
    public ResponseEntity<PrePayment> getPrePayment(@PathVariable Long id) {
        log.debug("REST request to get PrePayment : {}", id);
        PrePayment prePayment = prePaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prePayment));
    }

    /**
     * DELETE  /pre-payments/:id : delete the "id" prePayment.
     *
     * @param id the id of the prePayment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pre-payments/{id}")
    @Timed
    public ResponseEntity<Void> deletePrePayment(@PathVariable Long id) {
        log.debug("REST request to delete PrePayment : {}", id);
        prePaymentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
