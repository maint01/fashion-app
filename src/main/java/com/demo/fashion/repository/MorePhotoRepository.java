package com.demo.fashion.repository;

import com.demo.fashion.domain.MorePhoto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MorePhoto entity.
 */
@SuppressWarnings("unused")
public interface MorePhotoRepository extends JpaRepository<MorePhoto,Long> {

}
