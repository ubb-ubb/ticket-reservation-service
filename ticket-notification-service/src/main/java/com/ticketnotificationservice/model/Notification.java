package com.ticketnotificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue (strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
