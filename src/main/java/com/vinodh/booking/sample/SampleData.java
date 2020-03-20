package com.vinodh.booking.sample;

import com.vinodh.booking.entity.Section;
import com.vinodh.booking.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinodh Kumar T
 */
@Component
public class SampleData implements CommandLineRunner {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public void run(final String... args) throws Exception {
        List<Section> sections = new ArrayList<>();
        sectionRepository.saveAll(sections);
    }
}
