package com.vinodh.booking.db;

import com.vinodh.booking.entity.Section;
import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.entity.User;
import com.vinodh.booking.repository.SectionRepository;
import com.vinodh.booking.repository.SlotRepository;
import com.vinodh.booking.repository.UserRepository;
import com.vinodh.booking.util.SampleData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vinodh Kumar T
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SlotBookingDatabaseTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SlotRepository slotRepository;

    @Test
    public void checkConfigs() {
        Assert.assertNotNull(entityManager);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(sectionRepository);
        Assert.assertNotNull(slotRepository);
    }

    @Test
    public void section_repo_test() {
        final Section savedSection = loadSampleSection();
        final Section newSection = sectionRepository.findBySectionName(savedSection.getSectionName()).get();
        Assert.assertEquals(SampleData.sampleSection().getSectionName(), newSection.getSectionName());
    }

    @Test
    public void user_repo_test() {
        final User savedUser = loadSampleUser();
        Assert.assertNotNull(savedUser.getUserId());
        final User newUser = userRepository.findById(savedUser.getUserId()).get();
        Assert.assertEquals(SampleData.sampleUser().getFirstName(), newUser.getFirstName());
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saving_slot_without_section_or_user_info() {
        slotRepository.saveAndFlush(SampleData.sampleSlot());
    }

    @Test
    public void slot_repo_test() {
        final Slot savedSlot = fillSampleSlot();
        entityManager.getEntityManager().getTransaction().commit();
        final Slot newSlot = slotRepository.findById(savedSlot.getSlotId()).get();
        Assert.assertEquals(SampleData.sampleUser().getFirstName(), newSlot.getUser().getFirstName());
    }

    public void booking_slot_for_user() {
        // We need under which SECTION user is trying to book the slot.


        // We need User Information to whom we are booking the slot for.


        // We need to Reserve the Slot for user based on their Id.
    }

    @After
    public void cleanup() {
//        this.sectionRepository.flush();
//        this.userRepository.flush();
//        this.slotRepository.flush();
    }

    private Section loadSampleSection() {
        return sectionRepository.save(SampleData.sampleSection());
    }


    private User loadSampleUser() {
        return userRepository.save(SampleData.sampleUser());
    }

    private Slot fillSampleSlot() {
        Slot testSlot = SampleData.sampleSlot();
        testSlot.setSection(loadSampleSection());
        testSlot.setUser(loadSampleUser());
        return slotRepository.save(testSlot);
    }

}
