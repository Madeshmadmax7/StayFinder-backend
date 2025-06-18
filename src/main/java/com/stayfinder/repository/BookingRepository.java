package com.stayfinder.repository;

import com.stayfinder.entity.Booking;
import com.stayfinder.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(AppUser user);
}
