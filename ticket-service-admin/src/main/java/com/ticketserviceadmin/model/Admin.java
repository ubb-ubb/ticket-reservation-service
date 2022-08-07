package com.ticketserviceadmin.model;

import com.ticketserviceadmin.validator.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotEmpty
    @NotBlank
    private String password;


    @NotBlank
    @Email
    private String email;

    @OneToMany
    private List<Travel> travelList = new ArrayList<>();


    public void assignTicket(Travel travel) {
        travelList.add(travel);
    }


}
