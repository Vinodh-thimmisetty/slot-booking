package com.vinodh.booking.service;

import com.vinodh.booking.domain.SeatAvailability;

import java.util.List;

/**
 * @author Vinodh Kumar T
 */
public interface SeatAvailabilityService {
    List<SeatAvailability> loadSlots();
}
