package com.malyshev.clitch.service;

import com.malyshev.clitch.dao.MessageRepository;
import com.malyshev.clitch.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void addMessage(Message message) {


        messageRepository.save(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void removeMessageById(long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message getMessageById(long id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
