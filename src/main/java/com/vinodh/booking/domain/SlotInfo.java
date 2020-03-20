package com.vinodh.booking.domain;

import com.vinodh.booking.entity.Slot;
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
public class SlotInfo {
    private Long userId;
    public String sectionName, slotId, color;
    public boolean isBlocked, isBooked, isAvailable;

    public SlotInfo(Slot slot) {
        if (slot != null) {
            this.slotId = slot.getSlotId();
            this.isBlocked = slot.isBlocked();
            this.isBooked = slot.isBooked();
            this.isAvailable = slot.isAvailable();
            this.color = slot.getColor();
            if (slot.getUser() != null) {
                this.userId = slot.getUser().getUserId();
            }
            if (slot.getSection() != null) {
                this.sectionName = slot.getSection().getSectionName();
            }
        }
    }
}
