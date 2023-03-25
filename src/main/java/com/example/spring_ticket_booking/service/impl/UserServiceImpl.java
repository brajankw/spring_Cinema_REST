package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Role;
import com.example.spring_ticket_booking.entity.User;
import com.example.spring_ticket_booking.repository.RoleRepository;
import com.example.spring_ticket_booking.repository.UserRepository;
import com.example.spring_ticket_booking.service.UserService;
import com.example.spring_ticket_booking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
}
