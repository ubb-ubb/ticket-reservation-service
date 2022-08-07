package com.ticketservice.service;


import com.ticketservice.client.PaymentClient;
import com.ticketservice.dto.PaymentDto;
import com.ticketservice.model.*;
import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.model.enumeration.Gender;
import com.ticketservice.model.enumeration.PaymentStatus;
import com.ticketservice.producer.NotificationProducer;
import com.ticketservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TravelRepository travelRepository;
    private final PassengerRepository passengerRepository;
    private final PaymentClient paymentClient;
    private final CardRepository cardRepository;
    private final NotificationProducer notificationProducer;

    public Ticket buyTicket(Long userId, Long travelId, Long cardId, List<Passenger> passengerList) {


        Travel travel = travelRepository.findById(travelId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        Card card = cardRepository.findById(cardId).orElseThrow();

        Ticket ticket = new Ticket();
        Payment payment = new Payment();

        if (passengerList.size() > travel.getCapacity()) {
            log.info("Unable to buy tickets. Maximum capacity of this trip is reached.");
            return ticket;
        }

        if (passengerList.stream().filter(psg-> psg.getGender().equals(Gender.MALE)).count() > 2 && user.getCustomerType().equals(CustomerType.INDIVIDUAL)) {
            log.info("Unable to buy tickets. Maximum 2 male ticket for individual customers at once.");
            return ticket;
        };

        List<Passenger> list = passengerRepository.findAll().stream().filter(psg -> psg.getTravel().equals(travel) && psg.getUser().equals(user)).collect(Collectors.toList());

        log.info("Test size: " + list.size());

        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setUser(user);
        payment.setCard(card);


        if (paymentClient.paymentCheck(payment)) {

            log.info("Test size in payment: " + list.size());

            if (user.getCustomerType().equals(CustomerType.INDIVIDUAL) && list.size() + passengerList.size() > 5) {
                log.info("Maximum passenger list is reached for Individual Customer");
                return ticket;
            }

            if (user.getCustomerType().equals(CustomerType.CORPORATE) && list.size() + passengerList.size()  > 20) {
                log.info("Maximum passenger list is reached for Corporate Customer");
                return ticket;
            }

            passengerList.stream().forEach(elem -> elem.setTravel(travel));
            passengerList.stream().forEach(elem -> elem.setUser(user));
            passengerList.stream().forEach(elem -> passengerRepository.save(elem));


            travel.setCapacity(travel.getCapacity() - passengerList.size());

            ticket.setTravel(travel);
            ticket.setPassengerList(passengerList);
            ticket.setUser(user);
            ticket.setPaymentStatus(PaymentStatus.SUCCESS);
            ticket.setTicketInfo(travel.getDeparture() + travel.getArrival() + travel.getTravelDateTime().toString());
            notificationProducer.sendSms(ticket);
            log.info("Payment Successful.");

        } else {
            ticket.setPaymentStatus(PaymentStatus.FAIL);
            log.info("Payment Failed.");
            return ticket;
        }

        travelRepository.save(travel);
        return ticketRepository.save(ticket);
    }


    public List<Ticket> getUserTickets(long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        return ticketRepository.findByUser(user);
    }
}
