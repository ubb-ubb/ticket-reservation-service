package com.ticketservice.model;

import com.ticketservice.model.enumeration.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
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
