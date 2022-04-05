package com.malyshev.clitch.dao;

import com.malyshev.clitch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u WHERE u.username=:username")
    User getUserByUsername(@Param("username") String username);

    @Query("Select u.username from User u WHERE u.username=:username")
    String findUsername(@Param("username")String username);

    @Query("Select u.id from User u WHERE u.username=:username")
    Long findUserId(@Param("username")String username);

}