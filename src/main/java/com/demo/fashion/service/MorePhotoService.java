package com.demo.fashion.service;

import com.demo.fashion.domain.MorePhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing MorePhoto.
 */
public interface MorePhotoService {

    /**
     * Save a morePhoto.
     *
     * @param morePhoto the entity to save
     * @return the persisted entity
     */
    MorePhoto save(MorePhoto morePhoto);

    /**
     *  Get all the morePhotos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MorePhoto> findAll(Pageable pageable);

    /**
     *  Get the "id" morePhoto.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MorePhoto findOne(Long id);

    /**
     *  Delete the "id" morePhoto.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
