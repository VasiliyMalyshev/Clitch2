package com.malyshev.clitch.dao;

import com.malyshev.clitch.model.FriendConfirmed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendConfirmedRepository extends JpaRepository<FriendConfirmed, Long> {

    @Query(value = "Select user_id from friend_confirmed where user_id=:id and friend_id=:friendId union select friend_id from friend_confirmed where user_id=:id and friend_id=:friendId", nativeQuery = true)
    List<Long> findUserId(@Param("id")Long id, @Param("friendId") Long friendId);
}