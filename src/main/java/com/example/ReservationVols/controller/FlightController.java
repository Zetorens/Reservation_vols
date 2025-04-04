package com.example.ReservationVols.controller;

import com.example.ReservationVols.model.Flight;
import com.example.ReservationVols.repository.AirplaneRepository;
import com.example.ReservationVols.repository.CityRepository;
import com.example.ReservationVols.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.getAll());
        return "flights/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        return "flights/add";
    }

    @PostMapping("/add")
    public String addFlight(@ModelAttribute Flight flight,
                            @RequestParam Long airplaneId,
                            @RequestParam Long departureCityId,
                            @RequestParam Long arrivalCityId) {

        flight.setAirplane(airplaneRepository.findById(airplaneId).orElse(null));
        flight.setDepartureCity(cityRepository.findById(departureCityId).orElse(null));
        flight.setArrivalCity(cityRepository.findById(arrivalCityId).orElse(null));

        flightService.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{flightNumber}")
    public String showEditForm(@PathVariable String flightNumber, Model model) {
        Flight flight = flightService.getById(flightNumber);
        model.addAttribute("flight", flight);
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        return "flights/edit";
    }

    @PostMapping("/edit")
    public String updateFlight(@ModelAttribute Flight flight,
                               @RequestParam Long airplaneId,
                               @RequestParam Long departureCityId,
                               @RequestParam Long arrivalCityId) {

        flight.setAirplane(airplaneRepository.findById(airplaneId).orElse(null));
        flight.setDepartureCity(cityRepository.findById(departureCityId).orElse(null));
        flight.setArrivalCity(cityRepository.findById(arrivalCityId).orElse(null));

        flightService.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/delete/{flightNumber}")
    public String deleteFlight(@PathVariable String flightNumber) {
        flightService.delete(flightNumber);
        return "redirect:/flights";
    }

    @GetMapping("/sorted-by-airplane")
    public String listFlightsSortedByAirplane(Model model) {
        model.addAttribute("flights", flightService.getFlightsByAirplaneOrder());
        return "flights/list_by_airplane";
    }

    @GetMapping("/search-by-period")
    public String showSearchByPeriodForm() {
        return "flights/search_by_period";
    }

    @PostMapping("/search-by-period")
    public String processSearchByPeriod(@RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                        @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                        Model model) {
        model.addAttribute("flights", flightService.getFlightsBetween(start, end));
        return "flights/list_by_period";
    }

    @GetMapping("/flights-today")
    public String showFlightsForCityToday(Model model) {
        model.addAttribute("cities", cityRepository.findAll());
        return "flights/flight_today_form";
    }

    @PostMapping("/flights-today")
    public String searchFlightsForCityToday(@RequestParam Long cityId, Model model) {
        List<Flight> flights = flightService.getTodayFlightsByCity(cityId);
        model.addAttribute("flights", flights);
        model.addAttribute("city", cityRepository.findById(cityId).orElse(null));
        return "flights/flight_today_list";
    }
}
