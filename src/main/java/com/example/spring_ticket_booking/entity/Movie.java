package com.example.spring_ticket_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="movie")
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonProperty("room")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="room_id", referencedColumnName = "id")
    private Room room;

    @Column(name="title")
    private String title;

    @Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    @JsonProperty("ticket_price")
    @Column(name = "ticket_price")
    private double ticketPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private Set<Seat> seats = new HashSet<>();

}
