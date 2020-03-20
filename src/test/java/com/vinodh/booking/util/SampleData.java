package com.vinodh.booking.util;

import com.vinodh.booking.domain.UserInfo;
import com.vinodh.booking.entity.Section;
import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.entity.User;

import java.time.LocalDate;
import java.util.Collections;

/**
 * @author Vinodh Kumar T
 */
public class SampleData {

    public static Slot sampleSlot() {

        return Slot.builder()
                .slotId("1")
                .isAvailable(true)
                .isBlocked(false)
                .isBooked(false)
                .color("primary")
                .blockTill(LocalDate.now().plusDays(1))
                .build();
    }

    public static UserInfo sampleUserInfo() {
        return UserInfo.builder()
                .userId(123456L)
                .loginId("vinodh5052")
                .firstName("Vinodh Kumar")
                .lastName("Thimmisetty")
                .email("vinodh5052@gmail.com")
                .build();
    }

    public static User sampleUser() {
        return User.builder()
                .loginId("vinodh5052")
                .firstName("Vinodh Kumar")
                .lastName("Thimmisetty")
                .email("vinodh5052@gmail.com")
                .slot(Slot.builder().slotId("100").isAvailable(true).build())
                .build();
    }

    public static User sampleAdmin() {
        return User.builder()
                .loginId("vinodh5052")
                .firstName("Vinodh Kumar")
                .lastName("Thimmisetty")
                .email("vinodh5052@gmail.com")
                .isAdmin(true)
                .build();
    }

    public static Section sampleSection() {
        Section d1 = new Section();
        d1.setSectionId(1000L);
        d1.setSectionName("D1");
        d1.setRows(1);
        d1.setColumns(2);
        d1.setSectionColor("blue");
        return d1;
    }

    public static Section sampleSection2() {
        Section d1 = new Section();
        d1.setSectionId(1000L);
        d1.setSectionName("D1");
        d1.setRows(1);
        d1.setColumns(2);
        d1.setSectionColor("blue");
        d1.setSlots(Collections.singletonList(Slot.builder().slotId("1")
                .isAvailable(true).user(User.builder().firstName("Vinodh Kumar")
                        .isAdmin(false).build()).build()));
        return d1;
    }
}
