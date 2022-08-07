package com.ticketservice.dto;

import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.validator.UniqueEmail;
import com.ticketservice.validator.UniqueUsername;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@Getter
@Setter
public class UserDto {


    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 16, message = "Username must be 8-16 char long")
    @UniqueUsername
    private String username;


    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 32, message = "Password must be 8-32 char long")
    private String password;

    @NotEmpty
    @NotNull
    @NotBlank
    @Email
    @UniqueEmail (message = "This email is already registered.")
    private String email;

    @Enumerated (value = EnumType.STRING)
    private CustomerType customerType;



}
