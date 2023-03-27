package com.example.spring_ticket_booking.controller;

import com.example.spring_ticket_booking.entity.Movie;
import com.example.spring_ticket_booking.entity.Room;
import com.example.spring_ticket_booking.entity.Seat;
import com.example.spring_ticket_booking.service.MovieService;
import com.example.spring_ticket_booking.service.RoomService;
import com.example.spring_ticket_booking.service.SeatService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class MovieController {

    private MovieService movieService;
    private RoomService roomService;
    private SeatService seatService;

    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return movieService.findAll();
    }

    @GetMapping("/movies/{movieId}")
    public Object movieDetails(@PathVariable int movieId) {
        return movieService.movieDetails(movieId);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Map<String, String> request) {
        if (!(request.size() == 4 && request.containsKey("room_id") && request.containsKey("title") && request.containsKey("date") && request.containsKey("ticket_price"))) {
            throw new RuntimeException("Invalid request body.");
        }
        Movie theMovie = new Movie();
        Room theRoom = roomService.findById(Integer.parseInt(request.get("room_id")));
        theMovie.setId(0);
        theMovie.setTitle(request.get("title"));
        theMovie.setDate(Timestamp.valueOf(request.get("date") + ".0"));
        theMovie.setRoom(theRoom);
        theMovie.setTicketPrice(Double.parseDouble(request.get("ticket_price")));
        for (int i = 1; i <= theRoom.getRows(); i++) {
            for (int j = 1; j <= theRoom.getColumns(); j++) {
                Seat theSeat = new Seat();
                theSeat.setId(0);
                theSeat.setRow(i);
                theSeat.setColumn(j);
                theSeat.setSold(false);
                theSeat.setMovie(theMovie);
                seatService.save(theSeat);
            }
        }
        Movie dbMovie = movieService.save(theMovie);
        return dbMovie;
    }
}
