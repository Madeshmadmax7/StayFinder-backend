package com.stayfinder.service;

import com.stayfinder.entity.Hotel;
import com.stayfinder.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }

    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        Hotel existingHotel = getHotelById(id);
        existingHotel.setName(updatedHotel.getName());
        existingHotel.setCity(updatedHotel.getCity());
        existingHotel.setGuests(updatedHotel.getGuests());
        existingHotel.setBeds(updatedHotel.getBeds());
        existingHotel.setBathrooms(updatedHotel.getBathrooms());
        existingHotel.setPrice(updatedHotel.getPrice());
        existingHotel.setAmenities(updatedHotel.getAmenities());
        existingHotel.setMainImage(updatedHotel.getMainImage());
        existingHotel.setSubImages(updatedHotel.getSubImages());
        existingHotel.setDescription(updatedHotel.getDescription());
        existingHotel.setRating(updatedHotel.getRating());
        existingHotel.setAddress(updatedHotel.getAddress());
        return hotelRepository.save(existingHotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
