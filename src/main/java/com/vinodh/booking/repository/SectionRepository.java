package com.vinodh.booking.repository;

import com.vinodh.booking.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Vinodh Kumar T
 */
public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findBySectionName(String sectionName);
}
