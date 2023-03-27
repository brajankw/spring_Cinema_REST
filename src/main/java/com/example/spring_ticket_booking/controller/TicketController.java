package com.example.spring_ticket_booking.controller;

import com.example.spring_ticket_booking.entity.Movie;
import com.example.spring_ticket_booking.entity.Seat;
import com.example.spring_ticket_booking.entity.Ticket;
import com.example.spring_ticket_booking.entity.User;
import com.example.spring_ticket_booking.service.MovieService;
import com.example.spring_ticket_booking.service.SeatService;
import com.example.spring_ticket_booking.service.TicketService;
import com.example.spring_ticket_booking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TicketController {

    private TicketService ticketService;
    private UserService userService;
    private SeatService seatService;

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody Map<String, Integer> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Seat theSeat = seatService.findSeat(request);

        theSeat.setSold(true);
        Ticket theTicket = new Ticket();
        theTicket.setId(0);
        theTicket.setSeat(theSeat);
        theTicket.setUser(userService.findById(username));
        theTicket.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
        Ticket dbTicket = ticketService.save(theTicket);
        return dbTicket;
    }

    @DeleteMapping("/return")
    public User returnTicket(@RequestBody Map<String, Integer> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return ticketService.returnTicket(request, username);
    }


}
