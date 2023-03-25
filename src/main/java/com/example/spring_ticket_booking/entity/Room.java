package com.example.spring_ticket_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "columns")
    private int columns;

    @Column(name="rows")
    private int rows;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<Movie> movies = new HashSet<>();

    public Room(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }
}
