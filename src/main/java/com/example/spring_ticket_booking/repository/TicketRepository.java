package com.example.spring_ticket_booking.repository;

import com.example.spring_ticket_booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {}
