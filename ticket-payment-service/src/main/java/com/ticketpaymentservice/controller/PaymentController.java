package com.ticketpaymentservice.controller;

import com.ticketpaymentservice.dto.PaymentDto;
import com.ticketpaymentservice.model.Payment;
import com.ticketpaymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping( "/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

//    @PostMapping
//    public String paymentCheckId(Long userId, Long travelId,Long cardId) {
//
//        log.info(userId + " " + travelId + " card:" + cardId);
//
//        return "ok";
//
//    }

    @PostMapping
    public Boolean checkPayment(Payment payment) {
        log.info("Payment Service Controller");
        return paymentService.checkPayment(payment);
    }


}
