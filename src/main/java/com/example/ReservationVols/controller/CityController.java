package com.example.ReservationVols.controller;

import com.example.ReservationVols.model.City;
import com.example.ReservationVols.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public String listCities(Model model) {
        model.addAttribute("cities", cityService.getAll());
        return "cities/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("city", new City());
        return "cities/add";
    }

    @PostMapping("/add")
    public String addCity(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("city", cityService.getById(id));
        return "cities/edit";
    }

    @PostMapping("/edit")
    public String updateCity(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        cityService.delete(id);
        return "redirect:/cities";
    }
}
