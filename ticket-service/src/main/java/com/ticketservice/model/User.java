package com.ticketservice.model;

import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.model.enumeration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column
//    @Enumerated (value = EnumType.STRING)
    private CustomerType customerType;

    @Column
    private RoleType role;

    @OneToMany
    private List<Ticket> ticketList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Card> cardList = new ArrayList<>();




    public void assignTicket(Ticket ticket) {
        ticketList.add(ticket);
    }

    public void assignCard(Card card) {
        cardList.add(card);
    }


}
