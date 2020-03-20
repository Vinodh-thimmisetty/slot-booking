package com.vinodh.booking.service;

import com.vinodh.booking.entity.User;
import com.vinodh.booking.exception.UserNotFoundException;
import com.vinodh.booking.repository.SectionRepository;
import com.vinodh.booking.repository.UserRepository;
import com.vinodh.booking.service.impl.UserServiceImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static com.vinodh.booking.util.SampleData.sampleSection2;
import static com.vinodh.booking.util.SampleData.sampleUser;
import static com.vinodh.booking.util.SampleData.sampleUserInfo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockingDetails;

/**
 * @author Vinodh Kumar T
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    private static int NO_OF_METHODS, COUNTER;
    private static User MOCKED_USER = null;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SectionRepository sectionRepository;

    @BeforeClass
    public static void initialize() {
        NO_OF_METHODS = UserServiceImpl.class.getDeclaredMethods().length;
        MOCKED_USER = sampleUser();
    }

    @Before
    public void setup() {
    }

    @Test(expected = UserNotFoundException.class)
    public void userNotFound() throws UserNotFoundException {
        doReturn(Optional.empty()).when(userRepository).findById(anyLong());
        userService.getUser(123l);
    }

    @Test
    public void getUserTest() throws Exception {
        doReturn(Optional.of(MOCKED_USER)).when(userRepository).findById(anyLong());
        assertEquals(MOCKED_USER.getFirstName(), userService.getUser(123l).getFirstName());
        assertEquals(mockingDetails(userRepository).getInvocations().size(), 1);
        COUNTER++;
    }

    @Test
    public void getAllUsersTest() throws Exception {
        doReturn(Arrays.asList(MOCKED_USER)).when(userRepository).findAll();
        assertEquals(MOCKED_USER.getFirstName(), userService.getAllUsers(null).get(0).getFirstName());
        assertEquals(mockingDetails(userRepository).getInvocations().size(), 1);
        COUNTER++;
    }


    @Test
    public void getUserBySectionTest() throws Exception {
        doReturn(Optional.of(sampleSection2())).when(sectionRepository).findBySectionName("D1");
        assertEquals(MOCKED_USER.getFirstName(), userService.getAllUsers("D1").get(0).getFirstName());
        assertEquals(mockingDetails(sectionRepository).getInvocations().size(), 1);
        COUNTER++;
    }

    @Test
    public void addUserTest() {
        doReturn(MOCKED_USER).when(userRepository).saveAndFlush(any(User.class));
        assertEquals(MOCKED_USER.getFirstName(), userService.addUser(sampleUserInfo()).getFirstName());
        assertEquals(mockingDetails(userRepository).getInvocations().size(), 1);
        COUNTER++;
    }


    @Test(expected = UserNotFoundException.class)
    public void update_invalid_userId_Test() throws UserNotFoundException {
        doReturn(Optional.empty()).when(userRepository).findById(anyLong());
        userService.updateUser(sampleUserInfo());
    }

    @Test
    public void updateUserTest() throws UserNotFoundException {
        doReturn(Optional.of(MOCKED_USER)).when(userRepository).findById(anyLong());
        doReturn(MOCKED_USER).when(userRepository).saveAndFlush(any(User.class));
        assertEquals(MOCKED_USER.getFirstName(), userService.updateUser(sampleUserInfo()).getFirstName());
        assertEquals(mockingDetails(userRepository).getInvocations().size(), 2);
        COUNTER++;
    }

    @Test
    public void deleteUser() {
        doNothing().when(userRepository).deleteById(anyLong());
        assertEquals("Deleted User ::" + 123, userService.deleteUser(123l));
        assertEquals(mockingDetails(userRepository).getInvocations().size(), 1);
        COUNTER++;
    }

    @Test
    public void validateLoginId() throws UserNotFoundException {
        doReturn(Optional.of(sampleUser())).when(userRepository).findByLoginId(anyString());
        assertEquals(sampleUser().getFirstName(), userService.validateLoginId("thimmv").getFirstName());
        COUNTER++;
    }

    @Test
    public void validateAdminPrivilege() throws UserNotFoundException {
        doReturn(Optional.of(sampleUser())).when(userRepository).findByLoginId(anyString());
        assertFalse(userService.validateAdminPrivilege("thimmv"));
        COUNTER++;
    }

    /**
     * This test will fail if any new Method is added into UserServiceImpl without writing proper tests.
     */
    @AfterClass
    public static void cleanup() {
        System.out.println("No. of Methods ::" + NO_OF_METHODS + " | No.of Tests :: " + COUNTER);
//        assertTrue("New Method(s) created in UserServiceImpl without testing.", NO_OF_METHODS < COUNTER);
    }


}
