package com.examly.springapp.service;

import com.examly.springapp.model.Booking;
import com.examly.springapp.repository.BookingRepository; // Assume you have this repository
import com.examly.springapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
