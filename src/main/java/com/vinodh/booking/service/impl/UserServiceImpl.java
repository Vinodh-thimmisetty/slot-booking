package com.vinodh.booking.service.impl;

import com.vinodh.booking.domain.UserInfo;
import com.vinodh.booking.entity.Section;
import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.entity.User;
import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.UserNotFoundException;
import com.vinodh.booking.repository.SectionRepository;
import com.vinodh.booking.repository.UserRepository;
import com.vinodh.booking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vinodh Kumar T
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public UserInfo getUser(final Long userId) throws UserNotFoundException {
        return new UserInfo(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<UserInfo> getAllUsers(final String sectionName) throws SectionNotFoundException {
        List<User> userList;
        if (sectionName != null) {
            final Section section = sectionRepository.findBySectionName(sectionName).orElseThrow(SectionNotFoundException::new);
            userList = section.getSlots().stream().map(Slot::getUser).collect(Collectors.toList());
        } else {
            userList = userRepository.findAll();
        }
        return userList.stream().map(UserInfo::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserInfo addUser(final UserInfo userInfo) {
        return new UserInfo(userRepository.saveAndFlush(new User(userInfo)));
    }


    @Transactional
    @Override
    public UserInfo updateUser(UserInfo userInfo) throws UserNotFoundException {
        final Optional<User> userOptional = userRepository.findById(userInfo.getUserId());
        User oldUser = userOptional.orElseThrow(UserNotFoundException::new);
        oldUser.setFirstName(userInfo.getFirstName());
        oldUser.setLastName(userInfo.getLastName());
        oldUser.setEmail(userInfo.getEmail());
        oldUser.setPhone(userInfo.getPhone());
        return new UserInfo(userRepository.saveAndFlush(oldUser));
    }

    @Override
    public String deleteUser(final Long userId) {
        userRepository.deleteById(userId);
        return "Deleted User ::" + userId;
    }

    @Override
    public UserInfo validateLoginId(final String loginId) throws UserNotFoundException {
        return new UserInfo(userRepository.findByLoginId(loginId).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public boolean validateAdminPrivilege(final String loginId) throws UserNotFoundException {
        final UserInfo userInfo = validateLoginId(loginId);
        return userInfo.isAdmin();
    }

}
