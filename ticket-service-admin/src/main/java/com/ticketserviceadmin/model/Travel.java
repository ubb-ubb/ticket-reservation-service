package com.ticketserviceadmin.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketserviceadmin.model.enumerations.Status;
import com.ticketserviceadmin.model.enumerations.TransportationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "travel")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String arrival;


    @Column
    private String departure;

    @Column
    private Integer travelUnitPrice;

    @OneToMany
    @JsonIgnore
    private List<Ticket> ticketList = new ArrayList<>();

    @Column
    private LocalDateTime creationDateTime = LocalDateTime.now();

    @Column
    private LocalDateTime travelDateTime;

    @Column
    private TransportationType transportationType;

    @Column
    private Integer capacity;

    @ManyToOne
    @JsonIgnore
    private Admin admin;

    @Column
    private Status status = Status.ACTIVE;

}
