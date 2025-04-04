package com.example.ReservationVols.service;

import com.example.ReservationVols.model.Airplane;
import com.example.ReservationVols.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public List<Airplane> getAll() {
        return airplaneRepository.findAll();
    }

    @Override
    public Airplane getById(Long id) {
        return airplaneRepository.findById(id).orElse(null);
    }

    @Override
    public Airplane save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public void delete(Long id) {
        airplaneRepository.deleteById(id);
    }
}
