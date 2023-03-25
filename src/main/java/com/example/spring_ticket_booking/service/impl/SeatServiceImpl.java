package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Movie;
import com.example.spring_ticket_booking.entity.Room;
import com.example.spring_ticket_booking.entity.Seat;
import com.example.spring_ticket_booking.repository.SeatRepository;
import com.example.spring_ticket_booking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(int theId) {
        Optional<Seat> result = seatRepository.findById(theId);

        Seat theSeat = null;
        if (result.isPresent()) theSeat = result.get();
        else throw new RuntimeException("Did not find seat id: " + theId);

        return theSeat;
    }

    @Override
    public Seat save(Seat theSeat) {
        return seatRepository.save(theSeat);
    }

    @Override
    public void deleteById(int theId) {
        seatRepository.deleteById(theId);
    }

}
