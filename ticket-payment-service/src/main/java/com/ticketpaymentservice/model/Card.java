package com.ticketpaymentservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.TABLE)
    private Long id;


    @Column
    private String name;

    @Column
    private String no;

    @ManyToOne
    private User user;

}
