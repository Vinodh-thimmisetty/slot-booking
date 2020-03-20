package com.vinodh.booking;

import com.vinodh.booking.entity.User;
import com.google.gson.Gson;
import com.vinodh.booking.util.SampleData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SlotBookingApplicationTests {

    private static final String USER = "/v1/users/";
    private static final Long USER_ID = 1l;
    private static final String BOOKING = "/v1/booking/";
    private Gson gson = new Gson();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void userManagementTests() {
        // 1. Register User
        ResponseEntity<String> entityResponse = testRestTemplate.exchange(USER, POST, new HttpEntity<>(SampleData.sampleUser(), sampleHeaders()), String.class);
        Assert.assertEquals(CREATED, entityResponse.getStatusCode());
        String response = entityResponse.getBody();
//        Assert.assertEquals("", response);
        // 2. Get User
        entityResponse = testRestTemplate.exchange(USER + USER_ID, GET, new HttpEntity<>(sampleHeaders()), String.class);
        Assert.assertEquals(OK, entityResponse.getStatusCode());
        response = entityResponse.getBody();
//        Assert.assertEquals("", response);
        // 3. Update User
        entityResponse = testRestTemplate.exchange(USER + USER_ID, PUT, new HttpEntity<>(updatedUser(), sampleHeaders()), String.class);
        Assert.assertEquals(OK, entityResponse.getStatusCode());
        response = entityResponse.getBody();
//        Assert.assertEquals("", response);
        // 4. Delete User
        entityResponse = testRestTemplate.exchange(USER + USER_ID, DELETE, new HttpEntity<>(sampleHeaders()), String.class);
        Assert.assertEquals(OK, entityResponse.getStatusCode());
        response = entityResponse.getBody();
//        Assert.assertEquals("", response);
    }


    public void sectionManagementTests() {
        // 1. Register Section

        // a) Section ID Invalid


        // 2. Update Section

        // 3. Delete Section
    }

    public void slotManagementTests() {
        // 1. Register Slot

        // 2. Update Slot

        // 3. Delete Slot
    }


    private User updatedUser() {
        User u1 = SampleData.sampleUser();
        u1.setFirstName("Vinodh Kumar");
        return u1;
    }


    private HttpHeaders sampleHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }

}
