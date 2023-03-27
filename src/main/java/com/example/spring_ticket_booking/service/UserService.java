package com.example.spring_ticket_booking.service;

import com.example.spring_ticket_booking.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();

    User findById(String theId);

    User save(User theUser);

    void deleteById(String theId);
    Object manageUserRoles(User theUser, Map<String, Boolean> request);
    Object userDetails(String username);
}
