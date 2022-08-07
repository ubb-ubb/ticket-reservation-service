package com.ticketservice.controller;


import com.ticketservice.model.Passenger;
import com.ticketservice.model.Ticket;
import com.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
@RequiredArgsConstructor
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping(value = "/{userId}")
    public Ticket buyTicket(@PathVariable Long userId, @RequestParam Long travelId, @RequestParam Long cardId, @RequestBody List<Passenger> passengerList) {
        return ticketService.buyTicket(userId, travelId, cardId, passengerList);
    }

    @GetMapping(value = "/{userId}")
    public List<Ticket> getUserTickets(@PathVariable long userId) {
        return ticketService.getUserTickets(userId);

    }

}
