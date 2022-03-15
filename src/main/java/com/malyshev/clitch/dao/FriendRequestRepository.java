package com.malyshev.clitch.dao;

import com.malyshev.clitch.model.FriendRequest;
import com.malyshev.clitch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
