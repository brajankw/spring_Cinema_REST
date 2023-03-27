package com.example.spring_ticket_booking.controller;

import com.example.spring_ticket_booking.entity.Movie;
import com.example.spring_ticket_booking.entity.Seat;
import com.example.spring_ticket_booking.service.MovieService;
import com.example.spring_ticket_booking.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SeatController {

    private MovieService movieService;
    private SeatService seatService;

    @GetMapping("/seats")
    public Seat checkSeat(@RequestBody Map<String, Integer> request) {
        if (!(request.size() == 3 && request.containsKey("movie_id") && request.containsKey("row") && request.containsKey("column"))) {
            throw new RuntimeException("Invalid request body.");
        }
        Movie theMovie = movieService.findById(request.get("movie_id"));
        for (Seat theSeat: theMovie.getSeats()) {
            if (theSeat.getColumn() == request.get("column") && theSeat.getRow() == request.get("row")) return theSeat;
        }
        throw new RuntimeException("Seat don't exist!");
    }

    @GetMapping("/stats")
    public Object cinemaStats() {
        return seatService.cinemaStats();
    }
}
