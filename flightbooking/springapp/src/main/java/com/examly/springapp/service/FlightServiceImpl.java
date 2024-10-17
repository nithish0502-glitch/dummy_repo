package com.examly.springapp.service;

import com.examly.springapp.model.Flight;
import com.examly.springapp.repository.FlightRepository; // Assume you have this repository
import com.examly.springapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id " + id));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight updateFlight(Long id, Flight updatedFlight) {
        Flight existingFlight = getFlightById(id);
        // Update fields as necessary
        existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
        existingFlight.setAirline(updatedFlight.getAirline());
        existingFlight.setDepartureLocation(updatedFlight.getDepartureLocation());
        existingFlight.setArrivalLocation(updatedFlight.getArrivalLocation());
        existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
        existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
        existingFlight.setPrice(updatedFlight.getPrice());
        return flightRepository.save(existingFlight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
