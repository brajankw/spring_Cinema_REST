package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Role;
import com.example.spring_ticket_booking.entity.User;
import com.example.spring_ticket_booking.repository.RoleRepository;
import com.example.spring_ticket_booking.repository.UserRepository;
import com.example.spring_ticket_booking.service.UserService;
import com.example.spring_ticket_booking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser = null;
        if (result.isPresent()) theUser = result.get();
        else throw new RuntimeException("Did not find user id:" + theId);

        return theUser;
    }

    @Override
    public User save(User theUser) {
        if (userRepository.findById(theUser.getUsername()).isPresent()) {
            throw new RuntimeException("Username is taken!");
        }
        Role role = roleRepository.findByRole(Constants.Roles.CLIENT);

        if(role == null) {
            role = roleRepository.save(new Role(Constants.Roles.CLIENT));
        }
        theUser.setPassword("{noop}" + theUser.getPassword());
        theUser.setRoles(new HashSet<>(List.of(role)));
        theUser.setEnabled(true);
        return userRepository.save(theUser);
    }

    @Override
    public void deleteById(String theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public Object manageUserRoles(User theUser, Map<String, Boolean> request) {
        if (! (request.size() == 3 && request.containsKey(Constants.Roles.CLIENT) && request.containsKey(Constants.Roles.MANAGER) && request.containsKey(Constants.Roles.ADMIN))) throw new RuntimeException("Invalid request body!");
        List<Role> roles = new ArrayList<>();
        for (String key: request.keySet()) {
            if (request.get(key)) roles.add(roleRepository.findByRole(key));
        }
        theUser.setRoles(new HashSet<>(roles));
        userRepository.save(theUser);
        record rolesUpdate(String username, List<Role> user_roles) {}
        return new rolesUpdate(theUser.getUsername(), roles);
    }

    @Override
    public Object userDetails(String username) {
        // to be updated
        return findById(username);
    }
}
