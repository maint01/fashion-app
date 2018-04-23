package com.demo.fashion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.fashion.domain.CodPayment;
import com.demo.fashion.service.CodPaymentService;
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
 * REST controller for managing CodPayment.
 */
@RestController
@RequestMapping("/api")
public class CodPaymentResource {

    private final Logger log = LoggerFactory.getLogger(CodPaymentResource.class);

    private static final String ENTITY_NAME = "codPayment";
        
    private final CodPaymentService codPaymentService;

    public CodPaymentResource(CodPaymentService codPaymentService) {
        this.codPaymentService = codPaymentService;
    }

    /**
     * POST  /cod-payments : Create a new codPayment.
     *
     * @param codPayment the codPayment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new codPayment, or with status 400 (Bad Request) if the codPayment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cod-payments")
    @Timed
    public ResponseEntity<CodPayment> createCodPayment(@RequestBody CodPayment codPayment) throws URISyntaxException {
        log.debug("REST request to save CodPayment : {}", codPayment);
        if (codPayment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new codPayment cannot already have an ID")).body(null);
        }
        CodPayment result = codPaymentService.save(codPayment);
        return ResponseEntity.created(new URI("/api/cod-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cod-payments : Updates an existing codPayment.
     *
     * @param codPayment the codPayment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated codPayment,
     * or with status 400 (Bad Request) if the codPayment is not valid,
     * or with status 500 (Internal Server Error) if the codPayment couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cod-payments")
    @Timed
    public ResponseEntity<CodPayment> updateCodPayment(@RequestBody CodPayment codPayment) throws URISyntaxException {
        log.debug("REST request to update CodPayment : {}", codPayment);
        if (codPayment.getId() == null) {
            return createCodPayment(codPayment);
        }
        CodPayment result = codPaymentService.save(codPayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, codPayment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cod-payments : get all the codPayments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of codPayments in body
     */
    @GetMapping("/cod-payments")
    @Timed
    public ResponseEntity<List<CodPayment>> getAllCodPayments(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CodPayments");
        Page<CodPayment> page = codPaymentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cod-payments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cod-payments/:id : get the "id" codPayment.
     *
     * @param id the id of the codPayment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the codPayment, or with status 404 (Not Found)
     */
    @GetMapping("/cod-payments/{id}")
    @Timed
    public ResponseEntity<CodPayment> getCodPayment(@PathVariable Long id) {
        log.debug("REST request to get CodPayment : {}", id);
        CodPayment codPayment = codPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(codPayment));
    }

    /**
     * DELETE  /cod-payments/:id : delete the "id" codPayment.
     *
     * @param id the id of the codPayment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cod-payments/{id}")
    @Timed
    public ResponseEntity<Void> deleteCodPayment(@PathVariable Long id) {
        log.debug("REST request to delete CodPayment : {}", id);
        codPaymentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
