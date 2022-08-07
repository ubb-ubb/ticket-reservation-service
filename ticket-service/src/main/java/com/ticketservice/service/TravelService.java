package com.ticketservice.service;


import com.google.common.hash.Hashing;
import com.ticketservice.dto.TravelDto;
import com.ticketservice.model.Ticket;
import com.ticketservice.model.Travel;
import com.ticketservice.model.User;
import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.model.enumeration.RoleType;
import com.ticketservice.model.enumeration.TransportationType;
import com.ticketservice.repository.TicketRepository;
import com.ticketservice.repository.TravelRepository;
import com.ticketservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TravelRepository travelRepository;



    public List<TravelDto> getAllTravel() {

        List<TravelDto> postDtoList = Arrays.asList(modelMapper.map(travelRepository.findAll(), TravelDto[].class));
        return postDtoList;
    }

    public Travel getTravelsByParam(String departure, String arrival, String date, TransportationType type) {

        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);


        return travelRepository.findByDepartureAndArrivalAndTransportationTypeAndAndTravelDateTime(departure,arrival,type,dateTime);
    }
}
