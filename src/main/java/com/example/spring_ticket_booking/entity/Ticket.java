package com.example.spring_ticket_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="ticket")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonIgnore
    @JoinColumn(name="user_id", referencedColumnName = "username")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(mappedBy = "ticket")
    private Seat seat;

    @Column(name="purchase_date")
    private Timestamp purchaseDate;

}
