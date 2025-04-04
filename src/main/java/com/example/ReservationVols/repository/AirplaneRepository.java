package com.example.ReservationVols.repository;

import com.example.ReservationVols.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Optional<Airplane> findByName(String name);
}
