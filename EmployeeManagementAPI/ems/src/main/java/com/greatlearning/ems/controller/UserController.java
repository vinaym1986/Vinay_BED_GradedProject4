package com.greatlearning.ems.controller;

import com.greatlearning.ems.models.UserRequest;
import com.greatlearning.ems.security.entity.User;
import com.greatlearning.ems.security.entity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUserWithRole(@RequestBody UserRequest userRequest) {
        User createdUser = userService.createUserWithRole(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
