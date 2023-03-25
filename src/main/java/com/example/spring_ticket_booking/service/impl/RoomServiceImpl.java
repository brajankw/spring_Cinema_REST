package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Room;
import com.example.spring_ticket_booking.repository.RoomRepository;
import com.example.spring_ticket_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(int theId) {
        Optional<Room> result = roomRepository.findById(theId);

        Room theRoom = null;
        if (result.isPresent()) theRoom = result.get();
        else throw new RuntimeException("Did not find room id: " + theId);

        return theRoom;
    }

    @Override
    public Room save(Room theRoom) {
        return roomRepository.save(theRoom);
    }

    @Override
    public void deleteById(int theId) {
        roomRepository.deleteById(theId);
    }
}
