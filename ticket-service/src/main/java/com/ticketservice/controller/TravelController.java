package com.ticketservice.controller;


import com.ticketservice.dto.TravelDto;
import com.ticketservice.dto.UserDto;
import com.ticketservice.model.Ticket;
import com.ticketservice.model.Travel;
import com.ticketservice.model.enumeration.TransportationType;
import com.ticketservice.service.TicketService;
import com.ticketservice.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @GetMapping
    public List<TravelDto> getAllTravel() {
        return travelService.getAllTravel();

    }

    @PostMapping
    public Travel findByParams(@RequestParam String departure, @RequestParam String arrival, @RequestParam String date, @RequestParam TransportationType type) {

        return travelService.getTravelsByParam(departure, arrival, date, type);
    }


}
