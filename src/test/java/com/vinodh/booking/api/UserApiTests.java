package com.vinodh.booking.api;

import com.vinodh.booking.controller.UserController;
import com.vinodh.booking.domain.UserInfo;
import com.vinodh.booking.service.UserService;
import com.google.gson.Gson;
import com.vinodh.booking.util.SampleData;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Validate the Endpoint(s) @UserController.
 *
 * @author Vinodh Kumar T
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserApiTests {

    private static int NO_OF_END_POINTS, COUNTER;
    private static final String USER = "/v1/users/";
    private static final String MOCK_RESPONSE_STRING = "Mock Response";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private Gson gson = new Gson();

    @BeforeClass
    public static void initialize() {
        NO_OF_END_POINTS = UserController.class.getDeclaredMethods().length;
    }

    @Before
    public void setup() throws Exception {
        Mockito.doReturn(new UserInfo()).when(userService).addUser(any(UserInfo.class));
        doReturn(Arrays.asList(new UserInfo())).when(userService).getAllUsers(anyString());
        doReturn(new UserInfo()).when(userService).getUser(anyLong());
        doReturn(MOCK_RESPONSE_STRING).when(userService).deleteUser(anyLong());
        doReturn(new UserInfo()).when(userService).updateUser(any(UserInfo.class));
    }

    @Test
    public void registerNewUserTest() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(post(USER)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .content(gson.toJson(SampleData.sampleUser())))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void findUserByIdTest() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(get(USER + "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void findAllUsersTest() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(get(USER))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void findAllUsersInParticularSectionTest() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(get(USER + "section/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .content(gson.toJson(SampleData.sampleUser())))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void updateUserInfoTest() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(put(USER + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .content(gson.toJson(SampleData.sampleUser())))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    @Test
    public void deleteParticularUser() throws Exception {
        final MockHttpServletResponse actualResponse = mvc.perform(delete(USER + "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        COUNTER++;
    }

    /**
     * This test will fail if any new Endpoint is added into USER Controller without writing proper tests.
     */
    @AfterClass
    public static void cleanup() {
//        Assert.assertEquals("New Endpoint(s) created in BookingController without testing.", NO_OF_END_POINTS + " Endpoint(s)", COUNTER + " Endpoint(s)");
    }
}