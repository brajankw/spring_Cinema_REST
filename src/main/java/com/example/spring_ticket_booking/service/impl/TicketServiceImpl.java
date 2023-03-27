package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Ticket;
import com.example.spring_ticket_booking.entity.User;
import com.example.spring_ticket_booking.repository.TicketRepository;
import com.example.spring_ticket_booking.service.TicketService;
import com.example.spring_ticket_booking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private UserService userService;

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

    @Override
    public User returnTicket(Map<String, Integer> request, String username) {
        User theUser = userService.findById(username);
        if (!(request.size() == 1 && request.containsKey("ticket_id"))) throw new RuntimeException("Invalid request body");
        int id = request.get("ticket_id");
        Ticket theTicket = findById(id);
        if (!theTicket.getUser().getUsername().equals(username)) throw new RuntimeException("You don't have ticket with this id!");
        theTicket.getSeat().setSold(false);
        theTicket.setSeat(null);
        theTicket.setUser(null);
        deleteById(id);
        return theUser;
    }


}
