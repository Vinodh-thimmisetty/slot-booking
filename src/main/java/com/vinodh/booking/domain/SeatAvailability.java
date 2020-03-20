package com.vinodh.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vinodh Kumar T
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatAvailability {

    private Long sectionId;
    private String slotId;
    private boolean isAvailable;
}
