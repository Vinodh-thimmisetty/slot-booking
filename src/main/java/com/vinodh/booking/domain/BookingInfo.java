package com.vinodh.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Vinodh Kumar T
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingInfo {
    private List<SeatAvailability> seatAvailability;
}
