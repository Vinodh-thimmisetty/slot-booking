package com.vinodh.booking.domain;

import com.vinodh.booking.entity.Section;
import com.vinodh.booking.entity.Slot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

/**
 * @author Vinodh Kumar T
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionInfo {
    private static final Integer MIN_VALID_VALUE = 1;
    private Long sectionId;
    private String sectionName, sectionColor;
    private Integer rows, columns;
    private Integer totalCapacity, slotsFilled, slotsAvailable;
    private List<SlotInfo> slotInfo = new ArrayList<>();

    public SectionInfo(Section section) {
        if (section != null) {
            this.sectionId = section.getSectionId();
            this.sectionName = section.getSectionName();
            this.sectionColor = section.getSectionColor();
            this.rows = section.getRows();
            this.columns = section.getColumns();
            this.slotInfo = convertToDTO(section.getSlots());
            if (!this.slotInfo.isEmpty()) {
                SectionInfo sInfo = new SectionInfo(section);
                sInfo.setTotalCapacity(this.slotInfo.size());
                sInfo.setSlotsAvailable(toIntExact(this.slotInfo.stream().filter(SlotInfo::isAvailable).count()));
                sInfo.setSlotsFilled(toIntExact(this.slotInfo.stream().filter(SlotInfo::isBooked).count()));
                if (sInfo.getSlotsAvailable() < MIN_VALID_VALUE) {
                    sInfo.setSectionName("NOT AVAILABLE");
                    sInfo.setSectionColor("grey");
                }
            }
        }

    }

    private List<SlotInfo> convertToDTO(List<Slot> slots) {
        return slots.stream().map(SlotInfo::new).collect(Collectors.toList());
    }
}
