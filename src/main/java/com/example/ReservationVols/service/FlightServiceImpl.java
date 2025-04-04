package com.example.ReservationVols.service;

import com.example.ReservationVols.model.Flight;
import com.example.ReservationVols.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getById(String flightNumber) {
        return flightRepository.findById(flightNumber).orElse(null);
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void delete(String flightNumber) {
        flightRepository.deleteById(flightNumber);
    }

    @Override
    public List<Flight> getFlightsByAirplaneOrder() {
        return flightRepository.findAllByOrderByAirplane_Name();
    }

    @Override
    public List<Flight> getFlightsBetween(LocalDateTime start, LocalDateTime end) {
        return flightRepository.findByDepartureTimeBetween(start, end);
    }

    @Override
    public List<Flight> getTodayFlightsByCity(Long cityId) {
        return flightRepository.findTodayFlightsByDepartureCity(cityId);
    }
}
