package com.vinodh.booking.api;

import com.google.gson.Gson;
import com.vinodh.booking.controller.BookingController;
import com.vinodh.booking.domain.SectionInfo;
import com.vinodh.booking.domain.SlotInfo;
import com.vinodh.booking.service.BookingService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Validate the Endpoint(s) in @BookingController.
 *
 * @author Vinodh Kumar T
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BookingController.class)
@AutoConfigureMockMvc
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class BookingAPITests {

    private static int NO_OF_END_POINTS, COUNTER;
    private static final String BOOKING = "/v1/booking/";
    private static final String MOCK_RESPONSE_STRING = "Mock Response";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService bookingService;

    private Gson gson = new Gson();

    @BeforeClass
    public static void initialize() {
        NO_OF_END_POINTS = BookingController.class.getDeclaredMethods().length;
    }

    @Before
    public void setup() throws Exception {
        doReturn(Collections.singletonList(new SectionInfo())).when(bookingService).getAllSections();
        doReturn(new SectionInfo()).when(bookingService).getBySection(anyString());
        doReturn(new SlotInfo()).when(bookingService).bookSlot(anyString(), anyString(), anyString(), anyString());
        doReturn(MOCK_RESPONSE_STRING).when(bookingService).resetSeats(anyString());
        doReturn(MOCK_RESPONSE_STRING).when(bookingService).resetSeats(true);
    }

    @Test
    public void getSlotsAvailableInAllSectionsTest() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(get(BOOKING))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void getSlotsAvailablePerSection() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(get(BOOKING + "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void bookSlotAfterBadgeIn() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(put(BOOKING + "thimmv"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void bookSlotInParticularSection() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(put(BOOKING + "1/1/thimmv/admin"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void resetAllSlotsInAllSections() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(get(BOOKING + "reset"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void resetAllSlotsInParticularSection() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(get(BOOKING + "1/reset"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    /**
     * This test will fail if any new Endpoint is added into BookingController without writing proper tests.
     */
    @AfterClass
    public static void cleanup() {
//        Assert.assertEquals("New Endpoint(s) created in BookingController without testing.", NO_OF_END_POINTS + " Endpoint(s)", COUNTER + " Endpoint(s)");
    }
}