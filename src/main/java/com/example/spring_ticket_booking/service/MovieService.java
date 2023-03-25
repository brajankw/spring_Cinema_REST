package com.example.spring_ticket_booking.service;

import com.example.spring_ticket_booking.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    Movie findById(int theId);

    Movie save(Movie theMovie);

    void deleteById(int theId);
}
