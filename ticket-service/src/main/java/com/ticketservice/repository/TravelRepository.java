package com.ticketservice.repository;

import com.ticketservice.model.Travel;
import com.ticketservice.model.enumeration.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long> {

    Travel findByDepartureAndArrivalAndTransportationTypeAndAndTravelDateTime(String departure, String arrival, TransportationType transportationType, LocalDateTime localDateTime);



}
