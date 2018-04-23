package com.demo.fashion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.fashion.domain.MorePhoto;
import com.demo.fashion.service.MorePhotoService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MorePhoto.
 */
@RestController
@RequestMapping("/api")
public class MorePhotoResource {

    private final Logger log = LoggerFactory.getLogger(MorePhotoResource.class);

    private static final String ENTITY_NAME = "morePhoto";
        
    private final MorePhotoService morePhotoService;

    public MorePhotoResource(MorePhotoService morePhotoService) {
        this.morePhotoService = morePhotoService;
    }

    /**
     * POST  /more-photos : Create a new morePhoto.
     *
     * @param morePhoto the morePhoto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new morePhoto, or with status 400 (Bad Request) if the morePhoto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/more-photos")
    @Timed
    public ResponseEntity<MorePhoto> createMorePhoto(@Valid @RequestBody MorePhoto morePhoto) throws URISyntaxException {
        log.debug("REST request to save MorePhoto : {}", morePhoto);
        if (morePhoto.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new morePhoto cannot already have an ID")).body(null);
        }
        MorePhoto result = morePhotoService.save(morePhoto);
        return ResponseEntity.created(new URI("/api/more-photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /more-photos : Updates an existing morePhoto.
     *
     * @param morePhoto the morePhoto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated morePhoto,
     * or with status 400 (Bad Request) if the morePhoto is not valid,
     * or with status 500 (Internal Server Error) if the morePhoto couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/more-photos")
    @Timed
    public ResponseEntity<MorePhoto> updateMorePhoto(@Valid @RequestBody MorePhoto morePhoto) throws URISyntaxException {
        log.debug("REST request to update MorePhoto : {}", morePhoto);
        if (morePhoto.getId() == null) {
            return createMorePhoto(morePhoto);
        }
        MorePhoto result = morePhotoService.save(morePhoto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, morePhoto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /more-photos : get all the morePhotos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of morePhotos in body
     */
    @GetMapping("/more-photos")
    @Timed
    public ResponseEntity<List<MorePhoto>> getAllMorePhotos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MorePhotos");
        Page<MorePhoto> page = morePhotoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/more-photos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /more-photos/:id : get the "id" morePhoto.
     *
     * @param id the id of the morePhoto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the morePhoto, or with status 404 (Not Found)
     */
    @GetMapping("/more-photos/{id}")
    @Timed
    public ResponseEntity<MorePhoto> getMorePhoto(@PathVariable Long id) {
        log.debug("REST request to get MorePhoto : {}", id);
        MorePhoto morePhoto = morePhotoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(morePhoto));
    }

    /**
     * DELETE  /more-photos/:id : delete the "id" morePhoto.
     *
     * @param id the id of the morePhoto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/more-photos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMorePhoto(@PathVariable Long id) {
        log.debug("REST request to delete MorePhoto : {}", id);
        morePhotoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
