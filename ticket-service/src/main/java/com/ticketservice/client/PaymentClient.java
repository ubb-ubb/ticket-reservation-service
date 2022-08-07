package com.ticketservice.client;

import com.ticketservice.model.Passenger;
import com.ticketservice.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "paymentClient", url = "${payment.url}")
public interface PaymentClient {
//    @PostMapping(value = "/payment")
//    String paymentCheckId(Long userId, Long travelId,Long cardId);

    @PostMapping(value = "/payment")
    Boolean paymentCheck(Payment payment);



}
