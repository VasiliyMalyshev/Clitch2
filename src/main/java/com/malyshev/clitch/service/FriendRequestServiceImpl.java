package com.malyshev.clitch.service;

import com.malyshev.clitch.dao.FriendRequestRepository;
import com.malyshev.clitch.model.FriendRequest;
import com.malyshev.clitch.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private FriendRequestRepository friendRequestRepository;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    @Override
    public void addFriendRequest(FriendRequest friendRequest) {
            friendRequestRepository.save(friendRequest);
    }

    @Override
    public void updateFriendRequest(FriendRequest friendRequest) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
