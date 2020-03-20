package com.vinodh.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SlotBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlotBookingApplication.class, args);
	}

}
