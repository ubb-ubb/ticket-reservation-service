package com.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketservice.model.enumeration.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany (cascade = {CascadeType.ALL})
    private List<Passenger> passengerList = new ArrayList<>();

    @ManyToOne (cascade = {CascadeType.ALL})
    private Travel travel;

    @Column
    private String ticketInfo;

    @Enumerated (EnumType.STRING)
    private PaymentStatus paymentStatus;




}
