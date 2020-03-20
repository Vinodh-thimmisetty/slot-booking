package com.vinodh.booking.service;

import com.vinodh.booking.domain.UserInfo;
import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.UserNotFoundException;

import java.util.List;

/**
 * @author Vinodh Kumar T
 */
public interface UserService {
    UserInfo getUser(Long userId) throws UserNotFoundException;

    List<UserInfo> getAllUsers(String sectionName) throws SectionNotFoundException;

    UserInfo addUser(UserInfo user);

    UserInfo updateUser(UserInfo user) throws UserNotFoundException;

    String deleteUser(Long userId);

    UserInfo validateLoginId(String bookedFor) throws UserNotFoundException;

    boolean validateAdminPrivilege(String bookedBy) throws UserNotFoundException;
}
