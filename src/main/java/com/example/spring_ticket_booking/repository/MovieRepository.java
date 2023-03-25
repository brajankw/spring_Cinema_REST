package com.example.spring_ticket_booking.repository;

import com.example.spring_ticket_booking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {}
