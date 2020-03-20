package com.vinodh.booking.controller;

import com.vinodh.booking.domain.UserInfo;
import com.vinodh.booking.exception.SectionNotFoundException;
import com.vinodh.booking.exception.UserNotFoundException;
import com.vinodh.booking.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author Vinodh Kumar T
 */
@RestController
@RequestMapping("/v1/users")
@Api(tags = "API(s) to manage the User information")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get specific User")
    public ResponseEntity<UserInfo> getUser(@ApiParam @PathVariable Long userId) throws UserNotFoundException {
        final UserInfo response = service.getUser(userId);
        return new ResponseEntity<>(response, (!isEmpty(response)) ? OK : NOT_FOUND);
    }

    @GetMapping("/")
    @ApiOperation(value = "Get All Users")
    public ResponseEntity<List<UserInfo>> getAllUsers() throws SectionNotFoundException {
        final List<UserInfo> response = service.getAllUsers(null);
        return new ResponseEntity<>(response, (!isEmpty(response)) ? OK : NOT_FOUND);
    }

    @PostMapping("/")
    @ApiOperation(value = "Add new User")
    public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo user) {
        final UserInfo response = service.addUser(user);
        return new ResponseEntity<>(response, (!isEmpty(response)) ? CREATED : BAD_REQUEST);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "Update specific User")
    public ResponseEntity<UserInfo> updateUser(@ApiParam @PathVariable Long userId,
                                               @RequestBody UserInfo user) throws UserNotFoundException {
        user.setUserId(userId);
        final UserInfo response = service.updateUser(user);
        return new ResponseEntity<>(response, (!isEmpty(response)) ? OK : BAD_REQUEST);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "Delete specific User")
    public ResponseEntity<String> deleteUser(@ApiParam @PathVariable Long userId) {
        final String response = service.deleteUser(userId);
        return new ResponseEntity<>(response, (!isEmpty(response)) ? OK : BAD_REQUEST);
    }

    @GetMapping("/section/{sectionName}")
    @ApiOperation(value = "Find All Users in the specific section")
    public ResponseEntity<List<UserInfo>> getUsersBySection(@ApiParam @PathVariable String sectionName)
            throws SectionNotFoundException {
        final List<UserInfo> response = service.getAllUsers(sectionName);
        return new ResponseEntity<>(response, (!isEmpty(response)) ? OK : NOT_FOUND);
    }

}
