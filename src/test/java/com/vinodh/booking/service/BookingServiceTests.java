package com.vinodh.booking.service;

import com.vinodh.booking.domain.SlotInfo;
import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.SlotNotFoundException;
import com.vinodh.booking.repository.SectionRepository;
import com.vinodh.booking.repository.SlotRepository;
import com.vinodh.booking.repository.UserRepository;
import com.vinodh.booking.service.impl.BookingServiceImpl;
import com.vinodh.booking.util.SampleData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockingDetails;

/**
 * @author Vinodh Kumar T
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTests {

    private static int NO_OF_METHODS, COUNTER;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private SlotRepository slotRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
    }

    @Test(expected = SectionNotFoundException.class)
    public void accessing_invalid_section() throws Exception {
        doReturn(Optional.empty()).when(sectionRepository).findBySectionName(anyString());
        bookingService.getBySection("INVALID");
    }

    @Test
    public void getBySectionTest() throws Exception {
        doReturn(Optional.of(SampleData.sampleSection())).when(sectionRepository).findBySectionName("D1");
        assertEquals(SampleData.sampleSection().getSectionColor(), bookingService.getBySection("D1").getSectionColor());
        assertEquals(mockingDetails(sectionRepository).getInvocations().size(), 1);
        COUNTER++;
    }

    @Test
    public void getAllSectionsTest() {
        doReturn(Arrays.asList(SampleData.sampleSection())).when(sectionRepository).findAll();
        assertEquals(SampleData.sampleSection().getSectionColor(), bookingService.getAllSections().get(0).getSectionColor());
        assertEquals(mockingDetails(sectionRepository).getInvocations().size(), 1);
        COUNTER++;
    }

    @Test(expected = SlotNotFoundException.class)
    public void invalid_Slot_booking_Test() throws Exception {
        doReturn(Optional.of(SampleData.sampleUser())).when(userRepository).findByLoginId("thimmv");
        doReturn(Optional.of(SampleData.sampleAdmin())).when(userRepository).findByLoginId("admin");
        doReturn(Optional.of(SampleData.sampleSection())).when(sectionRepository).findBySectionName("D1");
        assertEquals(SampleData.sampleUser().getUserId(), bookingService.bookSlot("D1", "123", "thimmv", "admin").getUserId());
        assertEquals(mockingDetails(sectionRepository).getInvocations().size(), 2);
        COUNTER++;
    }

    @Test
    public void bookSlotTest() throws Exception {
        doReturn(Optional.of(SampleData.sampleUser())).when(userRepository).findByLoginId("thimmv");
        doReturn(Optional.of(SampleData.sampleAdmin())).when(userRepository).findByLoginId("admin");
        doReturn(Optional.of(SampleData.sampleSection2())).when(sectionRepository).findBySectionName("D1");
        doReturn(Slot.builder().slotId("1").isBooked(true).build()).when(slotRepository).saveAndFlush(any(Slot.class));
        final SlotInfo slotInfo = bookingService.bookSlot("D1", "1", "thimmv", "admin");
        Assert.assertTrue(slotInfo.isBooked());
        Assert.assertFalse(slotInfo.isAvailable());
        assertEquals(mockingDetails(userRepository).getInvocations().size(), 2);
        assertEquals(mockingDetails(sectionRepository).getInvocations().size(), 1);
        assertEquals(mockingDetails(slotRepository).getInvocations().size(), 1);
        COUNTER++;
    }

    @Test
    public void bookSlotAfterEmployeeBadgeIn() throws Exception {
        doReturn(Optional.of(SampleData.sampleUser())).when(userRepository).findByLoginId("thimmv");
        doReturn(Slot.builder().slotId("100").isBooked(true).isAvailable(false).build()).when(slotRepository).saveAndFlush(any(Slot.class));
        final SlotInfo slotInfo = bookingService.bookSlot("thimmv");
        Assert.assertTrue(slotInfo.isBooked());
        Assert.assertFalse(slotInfo.isAvailable());
        assertEquals(mockingDetails(userRepository).getInvocations().size(), 1);
        assertEquals(mockingDetails(slotRepository).getInvocations().size(), 1);
        COUNTER++;
    }


    //    @Test(expected = SlotNotFoundException.class)
    public void accessing_invalid_slot() {

    }

}
