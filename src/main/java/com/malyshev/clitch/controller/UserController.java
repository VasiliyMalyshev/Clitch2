package com.malyshev.clitch.controller;

import com.malyshev.clitch.model.FriendRequest;
import com.malyshev.clitch.model.User;
import com.malyshev.clitch.service.FriendRequestService;
import com.malyshev.clitch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    private final FriendRequestService friendRequestService;

    @Autowired
    public UserController(UserService userService, FriendRequestService friendRequestService) {
        this.userService = userService;
        this.friendRequestService = friendRequestService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/friendRequest")
    public ResponseEntity<FriendRequest> requestFriend(@RequestBody FriendRequest friendRequest) {
        friendRequestService.addFriendRequest(friendRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}