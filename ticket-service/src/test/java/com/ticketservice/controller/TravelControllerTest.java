package com.ticketservice.controller;

import com.ticketservice.dto.TravelDto;
import com.ticketservice.model.Travel;
import com.ticketservice.model.User;
import com.ticketservice.model.enumeration.Status;
import com.ticketservice.model.enumeration.TransportationType;
import com.ticketservice.repository.TravelRepository;
import com.ticketservice.service.TravelService;


import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TravelControllerTest {

    @InjectMocks
    private TravelController travelController;

    @Mock
    private TravelService travelService;



    private TravelDto travelDto = new TravelDto();
    private Travel travel = new Travel();

    private List<TravelDto> mockTravelDtoList = new ArrayList<>();


    @BeforeEach
    public void setUp() {


        travelDto.setArrival("Budapest");
        travelDto.setDeparture("Istanbul");
        travelDto.setStatus(Status.ACTIVE);
        travelDto.setTransportationType(TransportationType.BUSRIDE);
        travelDto.setTravelDateTime(LocalDateTime.of(2022, 8, 30, 19, 00));

        mockTravelDtoList.add(travelDto);

        travel.setArrival("Ankara");
        travel.setDeparture("Istanbul");


    }


    @Test
    @DisplayName("Find travel by Parameters")
    void givenParameters_whenGetMethod_thenReturnSavedTravel() {

        String departure = "Bolu";
        String arrival = "Ankara";
        String date = "2022-08-15 19:00";
        TransportationType type = TransportationType.FLIGHT;

        Travel mockTravel = new Travel();
        mockTravel.setTransportationType(type);
        mockTravel.setDeparture(departure);
        mockTravel.setArrival(arrival);
        mockTravel.setTravelDateTime(LocalDateTime.of(2022,8,15,19,0));


        Mockito.when(travelService.getTravelsByParam(departure, arrival, date, type)).thenReturn(mockTravel);

        Travel response = travelController.findByParams(departure,arrival,date,type);

        assertEquals(response.getArrival(), mockTravel.getArrival());
        assertEquals(response.getTravelDateTime(), mockTravel.getTravelDateTime());


    }

    @Test
    @DisplayName("This method should return all travel dto as list.")
    void whenGetMethod_thenReturnAllTravel() {

        Mockito.when(travelService.getAllTravel()).thenReturn(mockTravelDtoList);

        List<TravelDto> response = travelController.getAllTravel();

        assertEquals(response.size(), 1);
        assertEquals(response.stream().findAny().get().getArrival(), "Budapest");
        assertEquals(response.stream().findAny().get().getDeparture(), "Istanbul");

    }

}
