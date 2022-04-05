package com.malyshev.clitch.service;

import com.malyshev.clitch.dao.FriendConfirmedRepository;
import com.malyshev.clitch.dao.FriendRequestRepository;
import com.malyshev.clitch.dao.UserRepository;
import com.malyshev.clitch.exception_handling.AlreadySentException;
import com.malyshev.clitch.model.FriendConfirmed;
import com.malyshev.clitch.model.FriendRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final FriendConfirmedRepository friendConfirmedRepository;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository, FriendConfirmedRepository friendConfirmedRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
        this.friendConfirmedRepository = friendConfirmedRepository;
    }

    @Override
    public void addFriendRequest(FriendRequest friendRequest) {

        if (friendRequest.getUser() == null) {
            throw new AlreadySentException("User is null");
        } else if (friendRequest.getFriend() == null) {
            throw new AlreadySentException("Friend is null");
        }

        String username = userRepository.findUsername(friendRequest.getUser().getUsername());
        String friendName = userRepository.findUsername(friendRequest.getFriend().getUsername());

        Long userId = userRepository.findUserId(friendRequest.getUser().getUsername());
        Long friendId = userRepository.findUserId(friendRequest.getFriend().getUsername());

        List<Long> uf = Arrays.asList(userId, friendId);

        List<Long> userRequestId = friendRequestRepository.findUserId(userId, friendId);
        List<Long> userRequestIdReversed = new ArrayList<>(userRequestId);
        Collections.reverse(userRequestIdReversed);

        List<Long> userConfirmedId = friendConfirmedRepository.findUserId(userId, friendId);
        List<Long> userConfirmedIdReversed = new ArrayList<>(userConfirmedId);
        Collections.reverse(userConfirmedIdReversed);

        if (uf.equals(userConfirmedId) || uf.equals(userConfirmedIdReversed)) {
            throw new AlreadySentException("Пользователи " + username + " и " + friendName + " уже друзья");
        } else  if (uf.equals(userRequestId) || uf.equals(userRequestIdReversed)) {
            throw new AlreadySentException("Запрос от " + username + " к " + friendName + " был уже отправлен");
        } else {
            friendRequestRepository.save(friendRequest);
        }
    }

    @Override
    public void updateFriendRequest(FriendRequest friendRequest) {
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public void removeFriendRequestById(long id) {
        friendRequestRepository.deleteById(id);
    }

    @Override
    public FriendRequest getFriendRequestById(long id) {
        return friendRequestRepository.findById(id).get();
    }

    @Override
    public List<FriendRequest> getAllFriendRequest() {
        return friendRequestRepository.findAll();
    }
}
