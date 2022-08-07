package com.ticketnotificationservice.service;

import com.ticketnotificationservice.model.Mail;
import com.ticketnotificationservice.model.User;

public interface EmailService {
    public Mail sendEmail(User user);
}
