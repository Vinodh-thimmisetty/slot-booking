package com.vinodh.booking.service;

import com.vinodh.booking.domain.SectionInfo;
import com.vinodh.booking.domain.SlotInfo;
import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.SlotNotAvailableException;
import com.vinodh.booking.exception.SlotNotFoundException;
import com.vinodh.booking.exception.UnAuthorizedException;
import com.vinodh.booking.exception.UserNotFoundException;

import java.util.List;

/**
 * @author Vinodh Kumar T
 */
public interface BookingService {
    List<SectionInfo> getAllSections();

    SectionInfo getBySection(final String sectionName) throws SectionNotFoundException;

    SlotInfo bookSlot(final String sectionName, final String slotId, final String bookedFor, final String bookedBy)
            throws SectionNotFoundException, SlotNotFoundException, UserNotFoundException, UnAuthorizedException,
            SlotNotAvailableException;

    String resetSeats(final String sectionName) throws SectionNotFoundException;

    String resetSeats(boolean isManual);

    SlotInfo bookSlot(String userId) throws UserNotFoundException, SectionNotFoundException, UnAuthorizedException,
            SlotNotFoundException, SlotNotAvailableException;
}
