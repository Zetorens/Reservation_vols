package com.example.ReservationVols.repository;

import com.example.ReservationVols.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {

    // Liste des vols triés par avion
    List<Flight> findAllByOrderByAirplane_Name();

    // Liste des vols entre deux dates
    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);

    // Vols du jour pour une ville donnée (ville de départ)
    @Query("SELECT f FROM Flight f WHERE f.departureCity.id = :cityId AND DATE(f.departureTime) = CURRENT_DATE")
    List<Flight> findTodayFlightsByDepartureCity(@Param("cityId") Long cityId);
}
