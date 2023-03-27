package com.example.spring_ticket_booking.service.impl;

import com.example.spring_ticket_booking.entity.Movie;
import com.example.spring_ticket_booking.entity.Room;
import com.example.spring_ticket_booking.entity.Seat;
import com.example.spring_ticket_booking.repository.MovieRepository;
import com.example.spring_ticket_booking.repository.SeatRepository;
import com.example.spring_ticket_booking.service.MovieService;
import com.example.spring_ticket_booking.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;
    private MovieRepository movieRepository;

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(int theId) {
        Optional<Seat> result = seatRepository.findById(theId);

        Seat theSeat = null;
        if (result.isPresent()) theSeat = result.get();
        else throw new RuntimeException("Did not find seat id: " + theId);

        return theSeat;
    }

    @Override
    public Seat save(Seat theSeat) {
        return seatRepository.save(theSeat);
    }

    @Override
    public void deleteById(int theId) {
        seatRepository.deleteById(theId);
    }

    @Override
    public Seat findSeat(Map<String, Integer> request) {
        Seat theSeat = null;
        if (request.size() == 3 && request.containsKey("movie_id") && request.containsKey("row") && request.containsKey("column")) {
            Optional<Movie> result = movieRepository.findById(request.get("movie_id"));
            if (result.isPresent()) {
                Movie theMovie = result.get();
                for (Seat seat: theMovie.getSeats()) {
                    if (seat.getColumn() == request.get("column") && seat.getRow() == request.get("row")) theSeat = seat;
                }
            }
        } else if (request.size() == 1 && request.containsKey("id")) {
            theSeat = findById(request.get("id"));
        }
        if (theSeat == null) throw new RuntimeException("Invalid request body.");
        return theSeat;
    }

    @Override
    public Object cinemaStats() {
        double cinema_earnings = 0;
        int tickets_sold = 0;
        for (Seat seat: findAll()) {
            if (seat.isSold()) {
                tickets_sold++;
                cinema_earnings += seat.getMovie().getTicketPrice();
            }
        }
        record Stats(double cinema_earnings, int tickets_sold){}
        return new Stats(cinema_earnings, tickets_sold);
    }
}
