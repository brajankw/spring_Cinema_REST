package com.example.spring_ticket_booking.config;

import com.example.spring_ticket_booking.entity.User;
import com.example.spring_ticket_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findById(username);
        if (result.isPresent()) {
            User theUser = result.get();
            return new org.springframework.security.core.userdetails.User(theUser.getUsername()
                    , theUser.getPassword(),
                    theUser.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getRole()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
