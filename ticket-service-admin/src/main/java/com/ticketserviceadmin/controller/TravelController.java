package com.ticketserviceadmin.controller;

import com.ticketserviceadmin.model.Admin;
import com.ticketserviceadmin.model.Travel;
import com.ticketserviceadmin.service.AdminService;
import com.ticketserviceadmin.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("travel")
@RequiredArgsConstructor
public class TravelController {

    @Autowired
    private TravelService travelService;

    @PostMapping(value = "/{adminId}")
    public ResponseEntity<Travel> create (@RequestBody Travel travel,@PathVariable Long adminId) {

        return ResponseEntity.ok()
                .body(travelService.create(travel, adminId));
    }

    @PostMapping(value = "/cancel/{travelId}")
    public ResponseEntity<Travel> changeStatus (@PathVariable Long travelId) {

        return ResponseEntity.ok()
                .body(travelService.changeStatus(travelId));
    }

    @GetMapping(value = "/info")
    public ResponseEntity<String> getInfo (@RequestParam Long travelId) {

        return ResponseEntity.ok()
                .body(travelService.getTravelSalesInfo(travelId));
    }



    @GetMapping
    public List<Travel> getAllTravels () {
        return travelService.getAllTravels();
    }

    @GetMapping(value = "/{id}")
    public List<Travel> getAllTravelsByAdminId (@PathVariable Long id) {
        return travelService.getAllTravelsByAdminId(id);
    }


}
