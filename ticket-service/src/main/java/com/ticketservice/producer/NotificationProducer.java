package com.ticketservice.producer;

import com.ticketservice.model.Ticket;
import com.ticketservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public User sendToQueue(User user) {

        System.out.println("From producer: User name:" + user.getUsername());
        rabbitTemplate.convertAndSend("ticket-exchange","ticket-routingkey",user);
        return user;

}

public Ticket sendSms(Ticket ticket) {

    System.out.println("From producer: Ticket:" + ticket.getTicketInfo());
    rabbitTemplate.convertAndSend("ticket-exchange", "ticket-routingkey-sms",ticket);
    return ticket;
}

}
