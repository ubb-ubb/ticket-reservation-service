package com.ticketservice.dto;

import com.ticketservice.model.Ticket;
import com.ticketservice.model.enumeration.Status;
import com.ticketservice.model.enumeration.TransportationType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class TravelDto {

    @NotBlank
    @Id
    private Long id;

    @NotBlank
    private String arrival;

    @NotBlank
    private String departure;

    @Column
    @NotBlank
    private Integer travelUnitPrice;


    @Column
    private TransportationType transportationType;


    @Column
    @NotBlank
    private Status status;

    @Column
    private LocalDateTime travelDateTime;

    @Column
    private Integer capacity;


}
