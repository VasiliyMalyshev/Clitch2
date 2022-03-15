package com.malyshev.clitch.service;


import com.malyshev.clitch.model.FriendRequest;
import com.malyshev.clitch.model.User;

import java.util.List;

public interface FriendRequestService {

    void addFriendRequest(FriendRequest friendRequest);

    void updateFriendRequest(FriendRequest friendRequest);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

}