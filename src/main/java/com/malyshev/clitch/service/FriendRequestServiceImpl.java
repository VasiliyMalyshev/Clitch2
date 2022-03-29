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
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final FriendConfirmedRepository friendConfirmedRepository;
   // FriendConfirmed friendConfirmed = new FriendConfirmed();

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository, FriendConfirmedRepository friendConfirmedRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
        this.friendConfirmedRepository = friendConfirmedRepository;
    }

    @Override
    public void addFriendRequest(FriendRequest friendRequest) {

        String username = userRepository.findUsername(friendRequest.getUser().getUsername());
        String friendName = userRepository.findUsername(friendRequest.getFriend().getUsername());

        Long userId = userRepository.findUserId(friendRequest.getUser().getUsername());
        Long friendId = userRepository.findUserId(friendRequest.getFriend().getUsername());

        String userConfirmedId = friendConfirmedRepository.findUserId(userId, friendId);
        String friendConfirmedID = friendConfirmedRepository.findUserIdReversed(userId, friendId);

        String uf = String.valueOf(userId + "," +  friendId);
        String ufReversed = String.valueOf(friendId + "," + userId);
        String userRequestId = friendRequestRepository.findUserId(friendRequest.getUser().getId(), friendRequest.getFriend().getId());
        String userRequestIdReversed = friendRequestRepository.findUserIdReversed(friendRequest.getFriend().getId(), friendRequest.getUser().getId());

        if (uf.equals(userConfirmedId) || uf.equals(friendConfirmedID) || ufReversed.equals(userConfirmedId) || ufReversed.equals(friendConfirmedID)) {
            throw new AlreadySentException("Пользователи " + username + " и " + friendName + " уже друзья");
        } else  if (uf.equals(userRequestId) || ufReversed.equals(userRequestId) || uf.equals(userRequestIdReversed) || ufReversed.equals(userRequestIdReversed)) {
            throw new AlreadySentException("Запрос от " + username + " к " + friendName + " был уже отправлен");
        } else {
            friendRequestRepository.save(friendRequest);
        }
      //  Long friendRequestId = friendRequestRepository.findFriendId(friendRequest.getFriend().getId());
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
