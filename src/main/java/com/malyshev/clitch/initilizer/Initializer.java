package com.malyshev.clitch.initilizer;

import com.malyshev.clitch.model.FriendConfirmed;
import com.malyshev.clitch.model.FriendRequest;
import com.malyshev.clitch.model.User;
import com.malyshev.clitch.service.FriendConfirmedService;
import com.malyshev.clitch.service.FriendRequestService;
import com.malyshev.clitch.service.UserService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/*
@Component
public class Initializer {

    private final UserService userService;
    private final FriendRequestService friendRequestService;
    private final FriendConfirmedService friendConfirmedService;

    public Initializer(UserService userService, FriendRequestService friendRequestService, FriendConfirmedService friendConfirmedService) {
        this.userService = userService;
        this.friendRequestService = friendRequestService;
        this.friendConfirmedService = friendConfirmedService;
    }

    private void datainit() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userService.addUser(user);

        User user2 = new User();
        user2.setUsername("dude");
        user2.setPassword("dude");
        userService.addUser(user2);

        User user3 = new User();
        user3.setUsername("vasya");
        user3.setPassword("vasya");
        userService.addUser(user3);

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setUser(user);
        friendRequest.setFriend(user2);
        friendRequestService.addFriendRequest(friendRequest);

        FriendConfirmed friendConfirmed = new FriendConfirmed();
        friendConfirmed.setUser(user);
        friendConfirmed.setFriend(user2);
        friendConfirmedService.addFriendConfirmed(friendConfirmed);

    }

    @PostConstruct
    private void init() {
        datainit();
    }
}
*/