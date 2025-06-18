package com.stayfinder.controller;

import com.stayfinder.entity.Booking;
import com.stayfinder.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long userId,
            @RequestParam Long hotelId,
            @RequestBody Booking bookingRequest) {

        bookingRequest.setBookingDate(LocalDate.now());

        Booking savedBooking = bookingService.createBooking(userId, hotelId, bookingRequest);
        return ResponseEntity.ok(savedBooking);
    }


    @GetMapping
    public ResponseEntity<List<Booking>> getUserBookings(@RequestParam Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
