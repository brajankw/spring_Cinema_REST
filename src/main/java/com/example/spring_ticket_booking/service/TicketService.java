package com.example.spring_ticket_booking.service;

import com.example.spring_ticket_booking.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    Ticket findById(int theId);

    Ticket save(Ticket theTicket);

    void deleteById(int theId);
}
