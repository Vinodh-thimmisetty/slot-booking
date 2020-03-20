package com.vinodh.booking.service.impl;

import com.vinodh.booking.domain.SeatAvailability;
import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.repository.SlotRepository;
import com.vinodh.booking.service.SeatAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinodh Kumar T
 */
@Service
public class SeatAvailabilityServiceImpl implements SeatAvailabilityService {

    @Autowired
    private SlotRepository slotRepository;

    @Override
    public List<SeatAvailability> loadSlots() {
        List<SeatAvailability> seats = new ArrayList<>();
        for (final Slot slot : slotRepository.findAll()) {
            seats.add(SeatAvailability
                    .builder()
                    .sectionId(slot.getSection().getSectionId())
                    .slotId(slot.getSlotId())
                    .isAvailable(slot.isAvailable())
                    .build());
        }
        return seats;
    }
}
