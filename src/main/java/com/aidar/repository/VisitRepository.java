package com.aidar.repository;

import com.aidar.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aidar Shaifutdinov.
 */
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
