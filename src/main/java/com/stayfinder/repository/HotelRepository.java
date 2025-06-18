package com.stayfinder.repository;

import com.stayfinder.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCityIgnoreCase(String city);

    List<Hotel> findByNameContainingIgnoreCase(String name);
}
