package com.example.spring_ticket_booking.controller;

import com.example.spring_ticket_booking.entity.User;
import com.example.spring_ticket_booking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/account")
    public Object userDetails (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.userDetails(username);
    }

    @PutMapping("/roles/{username}")
    public Object manageUserRoles(@PathVariable String username, @RequestBody Map<String, Boolean> request) {
        User theUser = userService.findById(username);
        return userService.manageUserRoles(theUser, request);
    }

}
