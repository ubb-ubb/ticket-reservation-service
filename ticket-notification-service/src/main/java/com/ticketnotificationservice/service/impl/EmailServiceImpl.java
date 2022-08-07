package com.ticketnotificationservice.service.impl;

import com.ticketnotificationservice.model.Mail;
import com.ticketnotificationservice.model.Notification;
import com.ticketnotificationservice.model.ShortMessage;
import com.ticketnotificationservice.model.User;
import com.ticketnotificationservice.repository.NotificationRepository;
import com.ticketnotificationservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Mail sendEmail(User user) {

        Mail mail = new Mail();
        mail.setUser(user);
        mail.setMailBody("Registration is Successful for: " + user.getUsername());
        mail.setMailSubject("Welcome!");

        log.info("send email service: " + mail.getMailBody());
        return notificationRepository.save(mail);
    }
}
