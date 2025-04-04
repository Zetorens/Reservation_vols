package com.example.ReservationVols.service;

import com.example.ReservationVols.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<Flight> getAll();
    Flight getById(String flightNumber);
    Flight save(Flight flight);
    void delete(String flightNumber);

    List<Flight> getFlightsByAirplaneOrder();
    List<Flight> getFlightsBetween(LocalDateTime start, LocalDateTime end);
    List<Flight> getTodayFlightsByCity(Long cityId);
}
