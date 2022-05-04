package com.malyshev.clitch.dao;

import com.malyshev.clitch.model.Message;
import com.malyshev.clitch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
