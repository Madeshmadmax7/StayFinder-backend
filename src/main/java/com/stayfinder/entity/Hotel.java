package com.stayfinder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private int guests;
    private int beds;
    private int bathrooms;
    private double price;

    @ElementCollection
    private List<String> amenities;

    private String mainImage;

    @ElementCollection
    private List<String> subImages;

    @Column(length = 1000)
    private String description;

    private double rating;
    private String address;
}
