package com.ticketnotificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "shortMessages")
public class ShortMessage extends Notification{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    @Column
    private String smsBody;

}
