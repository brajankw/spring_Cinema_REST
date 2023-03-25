package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Ticket;
import com.example.spring_ticket_booking.repository.TicketRepository;
import com.example.spring_ticket_booking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(int theId) {
        Optional<Ticket> result = ticketRepository.findById(theId);

        Ticket theTicket = null;

        if (result.isPresent()) theTicket = result.get();
        else throw new RuntimeException("Did not find ticket id: " + theId);

        return theTicket;
    }

    @Override
    public Ticket save(Ticket theTicket) {
        return ticketRepository.save(theTicket);
    }

    @Override
    public void deleteById(int theId) {
        ticketRepository.deleteById(theId);
    }
}
