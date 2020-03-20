package com.vinodh.booking.repository;

import com.vinodh.booking.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vinodh Kumar T
 */
public interface SlotRepository extends JpaRepository<Slot, String> {
}
