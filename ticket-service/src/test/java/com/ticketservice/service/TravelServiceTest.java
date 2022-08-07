package com.ticketservice.service;

import com.ticketservice.dto.TravelDto;
import com.ticketservice.model.Travel;
import com.ticketservice.model.enumeration.TransportationType;
import com.ticketservice.repository.TravelRepository;
import com.ticketservice.repository.UserRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TravelServiceTest {

    @InjectMocks
    private TravelService travelService;

    @Mock
    private TravelRepository travelRepository;

    @Mock
    private ModelMapper modelMapper;

    TravelDto travelDto = new TravelDto();
    Travel travel = new Travel();

    @BeforeEach
    void setUp() {

        travelDto.setArrival("Mock Arrival");
        travelDto.setDeparture("Mock Departure");

        travel.setArrival("Mock Arrival");
        travel.setDeparture("Mock Departure");

    }

    @Test
    @DisplayName("It should return all travels")
    void whenGetAllTravel_returnListOfTravels() {


        TravelDto[] array = {travelDto};

        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(array);

        List<TravelDto> response = travelService.getAllTravel();

        assertEquals(response.size(),1);
        assertEquals(response.get(0).getArrival(),"Mock Arrival");

    }

    @Test
    @DisplayName("It should return travels with given parameters")
    void givenParameters_whenGetAllTravel_returnListOfTravels() {

        Mockito.when(travelRepository.findByDepartureAndArrivalAndTransportationTypeAndAndTravelDateTime(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(travel);



        Travel response = travelService.getTravelsByParam("Mock Departure","Mock Arrival","2022-05-20 19:00", TransportationType.FLIGHT);

        assertEquals(response.getArrival(),"Mock Arrival");

    }
}
