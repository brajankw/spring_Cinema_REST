package com.example.spring_ticket_booking.repository;

import com.example.spring_ticket_booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {}
