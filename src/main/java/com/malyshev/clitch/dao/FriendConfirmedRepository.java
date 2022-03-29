package com.malyshev.clitch.dao;

import com.malyshev.clitch.model.FriendConfirmed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendConfirmedRepository extends JpaRepository<FriendConfirmed, Long> {

    @Query("Select u.user.id, u.friend.id from FriendConfirmed u WHERE u.user.id=:id and u.friend.id=:friendId")
    String findUserId(@Param("id")Long id, @Param("friendId") Long friendId);

    @Query("Select u.friend.id, u.user.id from FriendConfirmed u WHERE u.friend.id=:friendId and u.user.id=:id")
    String findUserIdReversed(@Param("id")Long id,@Param("friendId") Long friendId);

    @Query("Select u.friend.id from FriendConfirmed u WHERE u.friend.id=:id")
    Long findFriendId(@Param("id")Long id);
}