package com.ticketpaymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotEmpty
    @NotBlank
    private String username;


    @NotEmpty
    @NotBlank
    private String password;

    @NotEmpty
    @NotNull
    @NotBlank
    @Email
    private String email;

    @OneToMany
    private List<Card> cardList = new ArrayList<>();


}
