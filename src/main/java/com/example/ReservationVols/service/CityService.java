package com.example.ReservationVols.service;

import com.example.ReservationVols.model.City;

import java.util.List;

public interface CityService {
    List<City> getAll();
    City getById(Long id);
    City save(City city);
    void delete(Long id);
}
