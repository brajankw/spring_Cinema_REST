package com.example.spring_ticket_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="seat")
@Getter
@Setter
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="sold")
    private boolean sold;

    @Column(name="row")
    private int row;

    @Column(name="col")
    private int column;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @JsonIgnore
    @OneToOne(mappedBy = "seat")
    private Ticket ticket;

}
