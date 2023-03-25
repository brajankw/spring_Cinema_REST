package com.example.spring_ticket_booking.service;

import com.example.spring_ticket_booking.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();

    Room findById(int theId);

    Room save(Room theRoom);

    void deleteById(int theId);
}
