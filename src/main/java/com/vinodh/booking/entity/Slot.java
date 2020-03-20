package com.vinodh.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.time.LocalDate;

/**
 * @author Vinodh Kumar T
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Slot {

    @Id
    private String slotId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SECTION_ID", nullable = false)
    private Section section;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    private String color;
    private boolean isBooked, isAvailable, isBlocked;
    private LocalDate blockFrom = LocalDate.now();
    private LocalDate blockTill = LocalDate.now().plusDays(1); // Default Block for ONE DAY

    private String bookedBy, bookedFor;

    @Version
    private long version;
}
