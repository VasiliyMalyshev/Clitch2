package com.malyshev.clitch.service;

import com.malyshev.clitch.model.FriendConfirmed;

import java.util.List;

public interface FriendConfirmedService {

    void addFriendConfirmed(FriendConfirmed friendConfirmed);

    void updateFriendConfirmed(FriendConfirmed friendConfirmed);

    void removeFriendConfirmedById(long id);

    FriendConfirmed getFriendConfirmedById(long id);

    List<FriendConfirmed> getAllFriendConfirmed();
}
