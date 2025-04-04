package com.example.ReservationVols.controller;

import com.example.ReservationVols.model.Airplane;
import com.example.ReservationVols.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping
    public String listAirplanes(Model model) {
        model.addAttribute("airplanes", airplaneService.getAll());
        return "airplanes/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        return "airplanes/add";
    }

    @PostMapping("/add")
    public String addAirplane(@ModelAttribute Airplane airplane) {
        airplaneService.save(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("airplane", airplaneService.getById(id));
        return "airplanes/edit";
    }

    @PostMapping("/edit")
    public String updateAirplane(@ModelAttribute Airplane airplane) {
        airplaneService.save(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/delete/{id}")
    public String deleteAirplane(@PathVariable Long id) {
        airplaneService.delete(id);
        return "redirect:/airplanes";
    }
}
