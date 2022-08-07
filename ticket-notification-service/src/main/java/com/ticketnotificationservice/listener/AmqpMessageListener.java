package com.ticketnotificationservice.listener;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketnotificationservice.model.Ticket;
import com.ticketnotificationservice.model.User;
import com.ticketnotificationservice.service.SmsService;
import com.ticketnotificationservice.service.impl.EmailServiceImpl;
import com.ticketnotificationservice.service.impl.SmsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class AmqpMessageListener {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private SmsServiceImpl smsService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "ticket-queue")
    public void sendMail(final Message message) {

        log.info("Rabbit MQ test for email");
        try {
            String rawData = new String(message.getBody());
            System.out.println(rawData);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            User user = objectMapper.readValue(rawData, User.class);
            log.info("Rabbit MQ user:" + user.getEmail() + user.getUsername());
            emailService.sendEmail(user);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "ticket-queue-sms")
    public void sendSms(final Message message) {

        log.info("Rabbit MQ test for sms");
        try {
            String rawData = new String(message.getBody());
            System.out.println(rawData);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Ticket ticket = objectMapper.readValue(rawData, Ticket.class);
            log.info("Rabbit MQ sms send:" + ticket.getTicketInfo());
            smsService.sendSms(ticket);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

