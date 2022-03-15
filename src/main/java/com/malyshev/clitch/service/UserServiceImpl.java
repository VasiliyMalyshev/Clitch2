package com.malyshev.clitch.service;

import com.malyshev.clitch.dao.UserRepository;
import com.malyshev.clitch.model.User;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
