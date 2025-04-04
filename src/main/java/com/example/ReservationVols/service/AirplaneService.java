package com.example.ReservationVols.service;

import com.example.ReservationVols.model.Airplane;

import java.util.List;

public interface AirplaneService {
    List<Airplane> getAll();
    Airplane getById(Long id);
    Airplane save(Airplane airplane);
    void delete(Long id);
}
