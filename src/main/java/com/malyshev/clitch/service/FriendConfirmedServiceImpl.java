package com.malyshev.clitch.service;

import com.malyshev.clitch.dao.FriendConfirmedRepository;
import com.malyshev.clitch.dao.FriendRequestRepository;
import com.malyshev.clitch.dao.UserRepository;
import com.malyshev.clitch.exception_handling.AlreadySentException;
import com.malyshev.clitch.model.FriendConfirmed;
import com.malyshev.clitch.model.FriendRequest;
import org.springframework.stereotype.Service;

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

        String uf = String.valueOf(userId + "," +  friendId);
        String ufReversed = String.valueOf(friendId + "," + userId);
        String userConfirmedId = friendConfirmedRepository.findUserId(friendConfirmed.getUser().getId(), friendConfirmed.getFriend().getId());
        String userConfirmedIdReversed = friendConfirmedRepository.findUserIdReversed(friendConfirmed.getFriend().getId(), friendConfirmed.getUser().getId());

        if (uf.equals(userConfirmedId) || ufReversed.equals(userConfirmedId) || uf.equals(userConfirmedIdReversed) || ufReversed.equals(userConfirmedIdReversed)) {
            throw new AlreadySentException("Пользователи " + username + " и " + friendName + " уже друзья");
        } else {
            friendConfirmedRepository.save(friendConfirmed);
        }
        //  Long friendRequestId = friendRequestRepository.findFriendId(friendRequest.getFriend().getId());
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
