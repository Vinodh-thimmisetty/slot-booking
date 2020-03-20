package com.vinodh.booking.controller;

/*
 *
 * @author Vinodh Kumar T
 */

import com.vinodh.booking.service.SeatAvailabilityService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/loadSlots")
@Api(tags = "API(s) to manage the seating arrangements")
@Slf4j
public class SlotAvailabilityController {

    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @GetMapping("/")
    public ResponseEntity<?> loadSlots() {
        return ResponseEntity.ok(seatAvailabilityService.loadSlots());
    }

}
