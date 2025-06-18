package com.stayfinder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Hotel hotel;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int guests;
    private double totalPrice;
    private LocalDate bookingDate;
}
