package com.vinodh.booking.jobs;

import com.vinodh.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vinodh Kumar T
 */
@Component
public class ResetSlots {

    @Autowired
    private BookingService bookingService;

//    @Scheduled(cron = "")
    public void resetSeats(){
        bookingService.resetSeats(false);
    }

}
