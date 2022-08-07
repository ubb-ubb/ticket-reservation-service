package com.ticketserviceadmin.service;

import com.ticketserviceadmin.model.Admin;
import com.ticketserviceadmin.model.Travel;
import com.ticketserviceadmin.model.enumerations.Status;
import com.ticketserviceadmin.model.enumerations.TransportationType;
import com.ticketserviceadmin.repository.AdminRepository;
import com.ticketserviceadmin.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    AdminRepository adminRepository;

    public Travel create(Travel travel, Long adminId) {

        Admin admin = adminRepository.findById(adminId).get();
        Integer capacity = (travel.getTransportationType() == TransportationType.FLIGHT) ? 189 : 45;
        travel.setCapacity(capacity);
        travel.setAdmin(admin);
        return travelRepository.save(travel);
    }

    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    public List<Travel> getAllTravelsByAdminId(Long id) {

        Admin admin = adminRepository.findById(id).get();

        return travelRepository.findByAdmin_Id(id);
//        return travelRepository.findByAdmin(admin);
    }


    public Travel changeStatus(Long travelId) {
        Travel travel = travelRepository.findById(travelId).get();
        travel.setStatus(Status.CANCELLED);
        return travelRepository.save(travel);
    }

    public String getTravelSalesInfo(Long travelId) {
        Travel travel = travelRepository.findById(travelId).get();
        int ticketCount;
        int ticketProfit;
        if (travel.getTransportationType().equals(TransportationType.FLIGHT)) {
            ticketCount = 189 - travel.getCapacity();
        } else {
            ticketCount = 45 - travel.getCapacity();
        }

        ticketProfit = ticketCount * travel.getTravelUnitPrice();

        return "Sold tickets: " + ticketCount + " Total Profit: " + ticketProfit;
    }
}
