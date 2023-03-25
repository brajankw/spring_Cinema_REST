package com.example.spring_ticket_booking.controller;

import com.example.spring_ticket_booking.entity.User;
import com.example.spring_ticket_booking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public User addUser(@RequestBody User theUser) {
        User dbEmployee = userService.save(theUser);
        return dbEmployee;
    }
}
