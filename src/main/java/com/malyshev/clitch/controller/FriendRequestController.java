package com.malyshev.clitch.controller;


import com.malyshev.clitch.model.FriendRequest;
import com.malyshev.clitch.model.User;
import com.malyshev.clitch.service.FriendRequestService;
import com.malyshev.clitch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/friendRequest")
@RestController
public class FriendRequestController {

    private final FriendRequestService friendRequestService;
    private final UserService userService;

    public FriendRequestController(FriendRequestService friendRequestService, UserService userService) {
        this.friendRequestService = friendRequestService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<FriendRequest> requestFriend(@RequestBody FriendRequest friendRequest) {
        friendRequestService.addFriendRequest(friendRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
