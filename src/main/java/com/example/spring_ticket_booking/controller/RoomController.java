package com.example.spring_ticket_booking.controller;

import com.example.spring_ticket_booking.entity.Room;
import com.example.spring_ticket_booking.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RoomController {

    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> findAllRooms() {
        return roomService.findAll();
    }

    @PostMapping("/rooms")
    public Room addRoom(@RequestBody Room theRoom) {
        theRoom.setId(0);
        Room dbRoom = roomService.save(theRoom);
        return dbRoom;
    }
}
