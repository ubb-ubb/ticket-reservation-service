package com.ticketservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User user;

}
