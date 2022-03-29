package com.malyshev.clitch.controller;

import com.malyshev.clitch.model.FriendConfirmed;
import com.malyshev.clitch.service.FriendConfirmedService;
import com.malyshev.clitch.service.FriendRequestService;
import com.malyshev.clitch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/friendConfirmed")
@RestController
public class FriendConfirmedRepositoryController {
    private final FriendRequestService friendRequestService;
    private final UserService userService;
    private final FriendConfirmedService friendConfirmedService;

    public FriendConfirmedRepositoryController(FriendRequestService friendRequestService, UserService userService, FriendConfirmedService friendConfirmedService) {
        this.friendRequestService = friendRequestService;
        this.userService = userService;
        this.friendConfirmedService = friendConfirmedService;
    }

    @PostMapping
    public ResponseEntity<FriendConfirmed> requestConfirmed(@RequestBody FriendConfirmed friendConfirmed) {
        friendConfirmedService.addFriendConfirmed(friendConfirmed);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
