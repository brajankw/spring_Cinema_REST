package com.example.spring_ticket_booking.repository;

import com.example.spring_ticket_booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {}
