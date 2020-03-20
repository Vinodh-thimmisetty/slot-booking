package com.vinodh.booking.repository;

import com.vinodh.booking.entity.BookingStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vinodh Kumar T
 */
public interface StatsRepository extends JpaRepository<BookingStatistics, Long> {
}
