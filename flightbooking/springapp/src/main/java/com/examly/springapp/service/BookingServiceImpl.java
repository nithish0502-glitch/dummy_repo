package com.examly.springapp.service;

import com.examly.springapp.exception.SeatsExceededException;
import com.examly.springapp.model.Booking;
import com.examly.springapp.model.Flight;
import com.examly.springapp.repository.BookingRepo; // Assume you have this repository
import com.examly.springapp.repository.FlightRepo;
import com.examly.springapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepository;

    @Autowired
    private FlightRepo flightRepository;
    
    @Override
public Booking createBooking(Booking booking) {
    Flight flight = flightRepository.findById(booking.getFlight().getFlightId())
            .orElseThrow(() -> new RuntimeException("Flight not found with id " + booking.getFlight().getFlightId()));

    // Check if there are enough seats available
    if (!flight.canBook(booking.getNumberOfPassengers())) {
        throw new SeatsExceededException("Cannot book " + booking.getNumberOfPassengers() +
                " passengers. Only " + (flight.getTotalSeats() - flight.getBookedSeats()) + " seats available.");
    }

    // Book the seats
    flight.bookSeats(booking.getNumberOfPassengers());
    flightRepository.save(flight); // Save the updated flight

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

    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepository.findByUser_UserId(userId);
    }

    @Override
    public Booking updateBooking(Long id, Booking updatedBooking) {
        // Booking existingBooking = getBookingById(id);
        // // Update the fields as necessary
        // existingBooking.setFlight(updatedBooking.getFlight());
        // existingBooking.setUser(updatedBooking.getUser());
        // existingBooking.setBookingDate(updatedBooking.getBookingDate());
        // existingBooking.setNumberOfPassengers(updatedBooking.getNumberOfPassengers());
        // existingBooking.setStatus(updatedBooking.getStatus());

        Booking oldone = bookingRepository.findById(id).orElse(null);
        oldone.setStatus(updatedBooking.getStatus());
        return bookingRepository.save(oldone);
    }
}
