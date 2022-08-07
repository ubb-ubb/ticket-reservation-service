package com.ticketpaymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Card card;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;


}
