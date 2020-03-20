package com.vinodh.booking.service.impl;

import com.vinodh.booking.domain.SectionInfo;
import com.vinodh.booking.domain.SlotInfo;
import com.vinodh.booking.entity.Availability;
import com.vinodh.booking.entity.BookingStatistics;
import com.vinodh.booking.entity.Section;
import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.entity.User;
import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.SlotNotAvailableException;
import com.vinodh.booking.exception.SlotNotFoundException;
import com.vinodh.booking.exception.UnAuthorizedException;
import com.vinodh.booking.exception.UserNotFoundException;
import com.vinodh.booking.repository.SectionRepository;
import com.vinodh.booking.repository.SlotRepository;
import com.vinodh.booking.repository.StatsRepository;
import com.vinodh.booking.repository.UserRepository;
import com.vinodh.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vinodh Kumar T
 */
@Service
public class BookingServiceImpl implements BookingService {

    private static final Integer MIN_VALID_VALUE = 0;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SectionInfo> getAllSections() {
        return sectionRepository.findAll()
                .stream()
                .filter(x -> x.getSectionId() > MIN_VALID_VALUE)
                .map(SectionInfo::new)
                .collect(Collectors.toList());
    }

    @Override
    public SectionInfo getBySection(final String sectionName) throws SectionNotFoundException {
        return new SectionInfo(sectionRepository.findBySectionName(sectionName)
                .orElseThrow(SectionNotFoundException::new));
    }

    @Override
    public SlotInfo bookSlot(final String userId) throws UserNotFoundException, SlotNotAvailableException {
        final User user = userRepository.findByLoginId(userId).orElseThrow(UserNotFoundException::new);
        final Slot slot = user.getSlot();
        if (!slot.isAvailable()) throw new SlotNotAvailableException();
        slot.setAvailable(false);
        slot.setBooked(true);
        slot.setColor("");
        slot.setBookedFor(userId);
        slot.setBookedBy(userId);
        return new SlotInfo(slotRepository.saveAndFlush(slot));
    }

    @Override
    @Transactional
    public SlotInfo bookSlot(final String sectionName, final String slotId,
                             final String bookedFor, final String bookedBy) throws SectionNotFoundException,
            SlotNotFoundException, UserNotFoundException, UnAuthorizedException, SlotNotAvailableException {
        final User admin = userRepository.findByLoginId(bookedBy).orElseThrow(UnAuthorizedException::new);
        if (!admin.isAdmin()) throw new UnAuthorizedException();
        final User user = userRepository.findByLoginId(bookedFor).orElseThrow(UserNotFoundException::new);
        final Section section = sectionRepository.findBySectionName(sectionName)
                .orElseThrow(SectionNotFoundException::new);
        final Optional<Slot> optionalSlot = section.getSlots().stream()
                .filter(x -> slotId.equalsIgnoreCase(x.getSlotId())).findFirst();
        final Slot slot = optionalSlot.orElseThrow(SlotNotFoundException::new);
        if (!slot.isAvailable()) throw new SlotNotAvailableException();
        slot.setAvailable(false);
        slot.setBooked(true);
        slot.setColor("");
        slot.setBookedFor(bookedFor);
        slot.setBookedBy(bookedBy);
        slot.setUser(user);
        return new SlotInfo(slotRepository.saveAndFlush(slot));
    }

    @Override
    public String resetSeats(final String sectionName) throws SectionNotFoundException {
        final Optional<Section> sectionOptional = sectionRepository.findBySectionName(sectionName);
        final Section section = sectionOptional.orElseThrow(SectionNotFoundException::new);
        reset(section);
        return "Reset completed";
    }

    @Override
    public String resetSeats(final boolean isManual) {
        sectionRepository.findAll()
                .stream()
                .filter(x -> x.getSectionId() > MIN_VALID_VALUE)
                .forEach(this::reset);
        return "Reset ALL completed";
    }

    private void reset(Section section) {

        // Save Summary of that particular day.
        statsRepository.saveAndFlush(BookingStatistics.builder()
                .section(new Availability(section))
                .build());

        // Send current day booking details to Manager.


        // Load excel for future blocking of seats


        // Reset Availability Flags
        final List<Slot> updatedSlots = section.getSlots()
                .stream()
                .filter(x -> ChronoUnit.DAYS.between(LocalDate.now(), x.getBlockTill()) == MIN_VALID_VALUE)
                .peek(slot -> {
                    slot.setAvailable(true);
                    slot.setBooked(false);
                    slot.setColor("primary");
                }).collect(Collectors.toList());
        slotRepository.saveAll(updatedSlots);
    }


}
