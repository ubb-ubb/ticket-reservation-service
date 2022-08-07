package com.ticketservice.controller;

import com.ticketservice.dto.UserDto;
import com.ticketservice.model.Card;
import com.ticketservice.model.Passenger;
import com.ticketservice.model.Ticket;
import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.model.enumeration.PaymentStatus;
import com.ticketservice.service.TicketService;
import com.ticketservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    private Ticket mockTicket = new Ticket();
    private Ticket mockTicket2 = new Ticket();
    private List<Ticket> mockTicketList = new ArrayList<>();


    @BeforeEach
    public void setUp() {

        mockTicket.setTicketInfo("Ticket info test");
        mockTicket.setId(3L);
        mockTicket.setPaymentStatus(PaymentStatus.SUCCESS);

        mockTicket2.setTicketInfo("Ticket info test2");
        mockTicket2.setId(2L);
        mockTicket2.setPaymentStatus(PaymentStatus.FAIL);

        mockTicketList.add(mockTicket);

        mockTicketList.add(mockTicket2);


    }

    @Test
    void givenParams_whenBuyTicket_thenReturnBoughtTicket() {

        Mockito.when(ticketService.buyTicket(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(mockTicket);

        List<Passenger> passengerList = new ArrayList<>();

        passengerList.add(new Passenger());
        passengerList.add(new Passenger());

        Ticket response = ticketController.buyTicket(1L,1L,1L, passengerList);

        assertEquals(response.getTicketInfo(), "Ticket info test");

    }

    @Test
    void givenParams_whenGetTicket_thenReturnBoughtTicket() {

        Mockito.when(ticketService.getUserTickets(Mockito.anyLong())).thenReturn(mockTicketList);

        List<Ticket> response = ticketController.getUserTickets(10L);

        assertEquals(response.size(), 2);
        assertEquals(response.get(0).getTicketInfo(), "Ticket info test");
    }
}
