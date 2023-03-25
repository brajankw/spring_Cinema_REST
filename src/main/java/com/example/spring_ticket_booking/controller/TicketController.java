package com.example.spring_ticket_booking.controller;

import com.example.spring_ticket_booking.entity.Movie;
import com.example.spring_ticket_booking.entity.Seat;
import com.example.spring_ticket_booking.entity.Ticket;
import com.example.spring_ticket_booking.service.MovieService;
import com.example.spring_ticket_booking.service.SeatService;
import com.example.spring_ticket_booking.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TicketController {

    private MovieService movieService;
    private SeatService seatService;
    private TicketService ticketService;

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody Map<String, Integer> request) {
        Seat theSeat = null;
        if (request.size() == 3 && request.containsKey("movie_id") && request.containsKey("row") && request.containsKey("column")) {
            Movie theMovie = movieService.findById(request.get("movie_id"));
            for (Seat seat: theMovie.getSeats()) {
                if (seat.getColumn() == request.get("column") && seat.getRow() == request.get("row")) theSeat = seat;
            }
        } else if (request.size() == 1 && request.containsKey("id")) {
            theSeat = seatService.findById(request.get("id"));
        }
        if (theSeat == null) throw new RuntimeException("Invalid request body.");
        theSeat.setSold(true);
        Ticket theTicket = new Ticket();
        theTicket.setId(0);
        theTicket.setSeat(theSeat);
        theTicket.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
        Ticket dbTicket = ticketService.save(theTicket);
        return dbTicket;
    }
}
