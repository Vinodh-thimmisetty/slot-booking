package com.vinodh.booking.controller;

import com.vinodh.booking.domain.SectionInfo;
import com.vinodh.booking.domain.SlotInfo;
import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.SlotNotAvailableException;
import com.vinodh.booking.exception.SlotNotFoundException;
import com.vinodh.booking.exception.UnAuthorizedException;
import com.vinodh.booking.exception.UserNotFoundException;
import com.vinodh.booking.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author Vinodh Kumar T
 */
@RestController
@RequestMapping("/v1/booking")
@Api(tags = "API(s) to manage the seating arrangements")
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    @ApiOperation(value = "Get All Section details")
    public ResponseEntity<List<SectionInfo>> getAllSections() {
        final List<SectionInfo> response = bookingService.getAllSections();
        return new ResponseEntity<>(response, (!isEmpty(response)) ? OK : NOT_FOUND);
    }

    @GetMapping("/{sectionName}")
    @ApiOperation(value = "Get specific Section details")
    public ResponseEntity<SectionInfo> getBySection(@ApiParam @PathVariable String sectionName)
            throws SectionNotFoundException {
        return ResponseEntity.ok(bookingService.getBySection(sectionName));
    }

    @PutMapping("/{sectionName}/{slotId}/{bookedFor}/{bookedBy}")
    @ApiOperation(value = "Book new Slot in specific Section")
    public ResponseEntity<SlotInfo> bookSlot(@ApiParam @PathVariable String sectionName,
                                             @ApiParam @PathVariable String slotId,
                                             @ApiParam @PathVariable String bookedFor, // user_id
                                             @ApiParam @PathVariable String bookedBy) throws SectionNotFoundException,
            SlotNotFoundException, UserNotFoundException, UnAuthorizedException, SlotNotAvailableException {
        return ResponseEntity.ok(bookingService.bookSlot(sectionName, slotId, bookedFor, bookedBy));
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "Book new Slot in specific Section")
    public ResponseEntity<SlotInfo> bookSlotForUser(@ApiParam @PathVariable String userId)
            throws UserNotFoundException, SectionNotFoundException, UnAuthorizedException, SlotNotFoundException,
            SlotNotAvailableException {
        return ResponseEntity.ok(bookingService.bookSlot(userId));
    }


    @GetMapping("/{sectionName}/reset")
    @ApiOperation(value = "Reset Slot availability in specific Sections")
    public ResponseEntity<String> resetBySection(@ApiParam @PathVariable String sectionName)
            throws SectionNotFoundException {
        return ResponseEntity.ok(bookingService.resetSeats(sectionName));
    }

    @GetMapping("/reset")
    @ApiOperation(value = "Reset Slot availability in All Sections")
    public ResponseEntity<String> resetAll() {
        final String response = bookingService.resetSeats(true);
        return new ResponseEntity<>(response, (!isEmpty(response)) ? OK : NOT_FOUND);
    }
}