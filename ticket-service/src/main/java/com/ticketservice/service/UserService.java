package com.ticketservice.service;

import com.google.common.hash.Hashing;
import com.ticketservice.dto.UserDto;
import com.ticketservice.model.Card;
import com.ticketservice.model.Ticket;
import com.ticketservice.model.User;
import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.model.enumeration.RoleType;
import com.ticketservice.producer.NotificationProducer;
import com.ticketservice.repository.CardRepository;
import com.ticketservice.repository.TicketRepository;
import com.ticketservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;
    private final CardRepository cardRepository;

    private final NotificationProducer notificationProducer;

    public UserDto register(UserDto request) {
        User user = modelMapper.map(request, User.class);
        user.setCustomerType(CustomerType.INDIVIDUAL);
        user.setRole(RoleType.BASIC);

        user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());

        notificationProducer.sendToQueue(user);

        userRepository.save(user);
        return request;    }

    public UserDto login(UserDto request) {

        User user = modelMapper.map(request, User.class);
        user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
        User userToCheck = userRepository.findByUsername(request.getUsername());
        if (userToCheck.getPassword().equalsIgnoreCase(user.getPassword()) && userToCheck.getUsername().equalsIgnoreCase(user.getUsername()) && userToCheck.getEmail().equalsIgnoreCase(user.getEmail())) {
            log.info("Login success");
        }
        else {
            log.info ("Login failed");
        }



        return request;
    }


    public Card addCard(Card request, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();

       // user.assignCard(request);
        userRepository.save(user);

        request.setUser(user);

        return cardRepository.save(request);

    }


}
