package com.example.spring_ticket_booking.repository;

import com.example.spring_ticket_booking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {}
