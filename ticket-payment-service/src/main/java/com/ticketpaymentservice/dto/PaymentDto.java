package com.ticketpaymentservice.dto;

import com.ticketpaymentservice.model.Card;
import com.ticketpaymentservice.model.PaymentStatus;
import com.ticketpaymentservice.model.User;

import javax.persistence.*;

public class PaymentDto {


    @OneToOne
    private User user;

    @OneToOne
    private Card card;



}
