package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Movie;
import com.example.spring_ticket_booking.entity.Seat;
import com.example.spring_ticket_booking.repository.MovieRepository;
import com.example.spring_ticket_booking.service.MovieService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int theId) {
        Optional<Movie> result = movieRepository.findById(theId);

        Movie theMovie = null;
        if (result.isPresent()) theMovie = result.get();
        else throw new RuntimeException("Did not find movie id: " + theId);

        return theMovie;
    }

    @Override
    public Movie save(Movie theMovie) {
        return movieRepository.save(theMovie);
    }

    @Override
    public void deleteById(int theId) {
        movieRepository.deleteById(theId);
    }

    @Override
    public Object movieDetails(int theId) {
        Movie theMovie = findById(theId);
        Set<Seat> availableSeats = new HashSet<>();
        for (Seat seat: theMovie.getSeats()) {
            if (!seat.isSold()) availableSeats.add(seat);
        }
        @JsonPropertyOrder({"movie", "available_seats"})
        record MovieDetails(Movie movie, @JsonProperty("available_seats") Set<Seat> availableSeats) {}
        return new MovieDetails(theMovie, availableSeats);
    }
}
