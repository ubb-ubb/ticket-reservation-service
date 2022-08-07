package com.ticketnotificationservice.service;

import com.ticketnotificationservice.model.Mail;
import com.ticketnotificationservice.model.ShortMessage;
import com.ticketnotificationservice.model.Ticket;
import com.ticketnotificationservice.model.User;

public interface SmsService {
    public ShortMessage sendSms(Ticket ticket);
}
