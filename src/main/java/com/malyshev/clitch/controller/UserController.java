package com.malyshev.clitch.controller;

import com.malyshev.clitch.model.User;
import com.malyshev.clitch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;

@RequestMapping(value = "/api")
@Controller
public class UserController {

    private final UserService userService;

    //User user1 = ;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<String> index() {
       // if (userService.getUserById(id).isPresent()) {
       //     return userService.getUserById(id).get();
       // } else {
            return ResponseEntity.ok("qwerty");
       // }
    }

    @PostMapping("/postbody")
    public ResponseEntity<User> postBody(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}