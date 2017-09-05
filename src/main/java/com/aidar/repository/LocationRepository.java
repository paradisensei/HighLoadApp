package com.aidar.repository;

import com.aidar.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aidar Shaifutdinov.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
