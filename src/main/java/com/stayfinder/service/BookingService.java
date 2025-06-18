package com.stayfinder.service;

import com.stayfinder.entity.AppUser;
import com.stayfinder.entity.Booking;
import com.stayfinder.entity.Hotel;
import com.stayfinder.repository.AppUserRepository;
import com.stayfinder.repository.BookingRepository;
import com.stayfinder.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Booking createBooking(Long userId, Long hotelId, Booking booking) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        booking.setUser(user);
        booking.setHotel(hotel);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
    AppUser user = appUserRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    return bookingRepository.findByUser(user);
    }

}
