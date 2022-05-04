package com.malyshev.clitch.service;

import com.malyshev.clitch.model.Message;
import com.malyshev.clitch.model.User;

import java.util.List;

public interface MessageService {

    void addMessage(Message message);

    void updateMessage(Message message);

    void removeMessageById(long id);

    Message getMessageById(long id);

    List<Message> getAllMessages();

}
