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
public class FriendConfirmedServiceImpl implements FriendConfirmedService {

    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final FriendConfirmedRepository friendConfirmedRepository;

    public FriendConfirmedServiceImpl(UserRepository userRepository, FriendRequestRepository friendRequestRepository, FriendConfirmedRepository friendConfirmedRepository) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.friendConfirmedRepository = friendConfirmedRepository;
    }

    @Override
    public void addFriendConfirmed(FriendConfirmed friendConfirmed) {

        String username = userRepository.findUsername(friendConfirmed.getUser().getUsername());
        String friendName = userRepository.findUsername(friendConfirmed.getFriend().getUsername());

        Long userId = userRepository.findUserId(friendConfirmed.getUser().getUsername());
        Long friendId = userRepository.findUserId(friendConfirmed.getFriend().getUsername());

        List<Long> uf = Arrays.asList(userId, friendId);

        List<Long> userConfirmedId = friendConfirmedRepository.findUserId(friendConfirmed.getUser().getId(), friendConfirmed.getFriend().getId());
        List<Long> userConfirmedIdReversed = new ArrayList<>(userConfirmedId);
        Collections.reverse(userConfirmedIdReversed);

        if (uf.equals(userConfirmedId) || uf.equals(userConfirmedIdReversed)) {
            throw new AlreadySentException("Пользователи " + username + " и " + friendName + " уже друзья");
        } else {
            friendConfirmedRepository.save(friendConfirmed);
        }
    }

    @Override
    public void updateFriendConfirmed(FriendConfirmed friendConfirmed) {

    }

    @Override
    public void removeFriendConfirmedById(long id) {

    }

    @Override
    public FriendConfirmed getFriendConfirmedById(long id) {
        return null;
    }

    @Override
    public List<FriendConfirmed> getAllFriendConfirmed() {
        return null;
    }
}
