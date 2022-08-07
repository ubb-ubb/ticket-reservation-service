package com.ticketservice.dto;

import com.ticketservice.model.Card;
import com.ticketservice.model.User;

import javax.persistence.OneToOne;

public class PaymentDto {


    @OneToOne
    private User user;

    @OneToOne
    private Card card;



}
