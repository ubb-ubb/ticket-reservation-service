package com.ticketnotificationservice.service.impl;

import com.ticketnotificationservice.model.*;
import com.ticketnotificationservice.repository.NotificationRepository;
import com.ticketnotificationservice.service.EmailService;
import com.ticketnotificationservice.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public ShortMessage sendSms(Ticket ticket) {

        log.info("Sms confirmation.");
        ShortMessage sms = new ShortMessage();

        // add travel info.
        sms.setSmsBody("Hi! This sms is confirmation for your travel." + ticket.getTicketInfo());


        return notificationRepository.save(sms);
    }
}
