package com.demo.fashion.service.impl;

import com.demo.fashion.service.MorePhotoService;
import com.demo.fashion.domain.MorePhoto;
import com.demo.fashion.repository.MorePhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing MorePhoto.
 */
@Service
@Transactional
public class MorePhotoServiceImpl implements MorePhotoService{

    private final Logger log = LoggerFactory.getLogger(MorePhotoServiceImpl.class);
    
    private final MorePhotoRepository morePhotoRepository;

    public MorePhotoServiceImpl(MorePhotoRepository morePhotoRepository) {
        this.morePhotoRepository = morePhotoRepository;
    }

    /**
     * Save a morePhoto.
     *
     * @param morePhoto the entity to save
     * @return the persisted entity
     */
    @Override
    public MorePhoto save(MorePhoto morePhoto) {
        log.debug("Request to save MorePhoto : {}", morePhoto);
        MorePhoto result = morePhotoRepository.save(morePhoto);
        return result;
    }

    /**
     *  Get all the morePhotos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MorePhoto> findAll(Pageable pageable) {
        log.debug("Request to get all MorePhotos");
        Page<MorePhoto> result = morePhotoRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one morePhoto by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MorePhoto findOne(Long id) {
        log.debug("Request to get MorePhoto : {}", id);
        MorePhoto morePhoto = morePhotoRepository.findOne(id);
        return morePhoto;
    }

    /**
     *  Delete the  morePhoto by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MorePhoto : {}", id);
        morePhotoRepository.delete(id);
    }
}
