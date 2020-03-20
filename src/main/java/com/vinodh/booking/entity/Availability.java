package com.vinodh.booking.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

import java.util.List;

/**
 * @author Vinodh Kumar T
 */
@Embeddable
@Setter
@Getter
public class Availability {
    private Long sectionId, totalFilled, totalEmpty;
    private String sectionName;

    public Availability(Section section) {
        if (section != null) {
            this.sectionId = section.getSectionId();
            this.sectionName = section.getSectionName();
            final List<Slot> tempSlots = section.getSlots();
            if (!tempSlots.isEmpty()) {
                this.totalFilled = tempSlots.stream().filter(Slot::isBooked).count();
                this.totalEmpty = tempSlots.stream().filter(Slot::isAvailable).count();
            }
        }
    }
}
