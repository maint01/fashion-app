package com.demo.fashion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.fashion.domain.Bank;
import com.demo.fashion.service.BankService;
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
 * REST controller for managing Bank.
 */
@RestController
@RequestMapping("/api")
public class BankResource {

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    private static final String ENTITY_NAME = "bank";
        
    private final BankService bankService;

    public BankResource(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * POST  /banks : Create a new bank.
     *
     * @param bank the bank to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bank, or with status 400 (Bad Request) if the bank has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/banks")
    @Timed
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) throws URISyntaxException {
        log.debug("REST request to save Bank : {}", bank);
        if (bank.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bank cannot already have an ID")).body(null);
        }
        Bank result = bankService.save(bank);
        return ResponseEntity.created(new URI("/api/banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /banks : Updates an existing bank.
     *
     * @param bank the bank to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bank,
     * or with status 400 (Bad Request) if the bank is not valid,
     * or with status 500 (Internal Server Error) if the bank couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/banks")
    @Timed
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bank) throws URISyntaxException {
        log.debug("REST request to update Bank : {}", bank);
        if (bank.getId() == null) {
            return createBank(bank);
        }
        Bank result = bankService.save(bank);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bank.getId().toString()))
            .body(result);
    }

    /**
     * GET  /banks : get all the banks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of banks in body
     */
    @GetMapping("/banks")
    @Timed
    public ResponseEntity<List<Bank>> getAllBanks(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Banks");
        Page<Bank> page = bankService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/banks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /banks/:id : get the "id" bank.
     *
     * @param id the id of the bank to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bank, or with status 404 (Not Found)
     */
    @GetMapping("/banks/{id}")
    @Timed
    public ResponseEntity<Bank> getBank(@PathVariable Long id) {
        log.debug("REST request to get Bank : {}", id);
        Bank bank = bankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bank));
    }

    /**
     * DELETE  /banks/:id : delete the "id" bank.
     *
     * @param id the id of the bank to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/banks/{id}")
    @Timed
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        log.debug("REST request to delete Bank : {}", id);
        bankService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
