package com.manjeet.Recipe_Management_System_.API.controller;

import com.manjeet.Recipe_Management_System_.API.model.User;
import com.manjeet.Recipe_Management_System_.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
        return userService.signup(user);
    }

}
