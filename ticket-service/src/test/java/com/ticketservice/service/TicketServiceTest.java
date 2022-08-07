package com.ticketservice.service;

import com.ticketservice.client.PaymentClient;
import com.ticketservice.model.*;
import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.model.enumeration.Gender;
import com.ticketservice.model.enumeration.PaymentStatus;
import com.ticketservice.producer.NotificationProducer;
import com.ticketservice.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TravelRepository travelRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private NotificationProducer notificationProducer;

    Travel travel = new Travel();
    User user = new User();
    Card card = new Card();
    Ticket ticket = new Ticket();

    Passenger passenger1 =new Passenger();
    Passenger passenger2 = new Passenger();

    List<Passenger> passengerList = new ArrayList<>();
    List<Ticket> ticketList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        travel.setDeparture("Departure Mock");
        travel.setArrival("Arrival Mock");
        travel.setCapacity(189);
        travel.setTravelDateTime(LocalDateTime.of(2022,05,19,19,0));

        user.setUsername("Mock Username");
        user.setEmail("mock@gmail.com");
        user.setCustomerType(CustomerType.INDIVIDUAL);

        card.setName("Mock card name");
        card.setNo("Mock no");

        passenger1.setNameSurname("Mockito Pass");
        passenger2.setNameSurname("Mockito Pass2");

        passenger1.setGender(Gender.MALE);
        passenger2.setGender(Gender.FEMALE);

        passengerList.add(passenger1);
        passengerList.add(passenger2);

        ticket.setTicketInfo("Mock Ticket");

        ticket.setPassengerList(passengerList);
        ticket.setUser(user);

        ticketList.add(ticket);


    }


    @Test
    @DisplayName("This method should return ticket, and save ticket to database.")

    void givenParameters_whenBuyTicket_saveAndReturnTicket() {

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(cardRepository.findById(Mockito.any())).thenReturn(Optional.of(card));
        Mockito.when(travelRepository.findById(Mockito.any())).thenReturn(Optional.of(travel));

        Mockito.when(paymentClient.paymentCheck(Mockito.any())).thenReturn(Boolean.TRUE);
        Mockito.when(notificationProducer.sendSms(Mockito.any())).thenReturn(ticket);
        Mockito.when(travelRepository.save(Mockito.any())).thenReturn(travel);
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);


        Ticket response = ticketService.buyTicket(1L,1L,1L,passengerList);

        assertEquals(response.getTicketInfo(), ticket.getTicketInfo());
        assertEquals(response.getPassengerList().size(), 2);

        System.out.println(response);




    }

    @Test
    @DisplayName("It should return fail.")
    void givenPaymentFalse_whenBuyTicket_saveAndReturnTicket() {

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(cardRepository.findById(Mockito.any())).thenReturn(Optional.of(card));
        Mockito.when(travelRepository.findById(Mockito.any())).thenReturn(Optional.of(travel));
        Mockito.when(paymentClient.paymentCheck(Mockito.any())).thenReturn(Boolean.FALSE);


        Ticket response = ticketService.buyTicket(1L,1L,1L,passengerList);

        assertEquals(response.getPaymentStatus(), PaymentStatus.FAIL);

        System.out.println(response);




    }

    @Test
    @DisplayName("It should list all tickets of user")
    void givenId_whenGetTicket_ReturnTicket() {

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        Mockito.when(ticketRepository.findByUser(Mockito.any())).thenReturn(ticketList);

        List<Ticket> response = ticketService.getUserTickets(1L);

        assertEquals(response.get(0).getUser(), user);



    }
}
