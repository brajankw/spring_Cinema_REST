package com.example.spring_ticket_booking.service;

import com.example.spring_ticket_booking.entity.Seat;

import java.util.List;
import java.util.Map;

public interface SeatService {
    List<Seat> findAll();

    Seat findById(int theId);

    Seat save(Seat theSeat);

    void deleteById(int theId);

    Seat findSeat(Map<String, Integer> request);

    Object cinemaStats();
}
