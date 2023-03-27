package com.example.spring_ticket_booking.service;

import com.example.spring_ticket_booking.entity.Ticket;
import com.example.spring_ticket_booking.entity.User;

import java.util.List;
import java.util.Map;

public interface TicketService {
    List<Ticket> findAll();

    Ticket findById(int theId);

    Ticket save(Ticket theTicket);

    void deleteById(int theId);

    User returnTicket(Map<String, Integer> request, String username);

}
