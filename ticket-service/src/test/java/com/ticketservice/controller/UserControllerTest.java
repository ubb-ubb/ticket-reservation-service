package com.ticketservice.controller;

import com.ticketservice.dto.UserDto;
import com.ticketservice.model.Card;
import com.ticketservice.model.enumeration.CustomerType;
import com.ticketservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserDto mockUserDto = new UserDto();
    private Card mockCard = new Card();

    @BeforeEach
    public void setUp() {

        mockUserDto.setEmail("ubb@gmail.com");
        mockUserDto.setUsername("testUser");
        mockUserDto.setCustomerType(CustomerType.CORPORATE);

        mockCard.setName("Umutcan");
        mockCard.setNo("4444");
        mockCard.setId(1L);


    }

    @Test
    void givenUserDto_whenRegisterMethod_thenReturnSavedUser() {

        Mockito.when(userService.register(Mockito.any())).thenReturn(mockUserDto);

        UserDto response = userController.register(mockUserDto).getBody();

        assertEquals(response.getUsername(), mockUserDto.getUsername());
    }


    @Test
    void givenUserDto_whenLoginMethod_thenReturnLoggedUser() {

        Mockito.when(userService.login(Mockito.any())).thenReturn(mockUserDto);

        UserDto response = userController.login(mockUserDto).getBody();

        assertEquals(response.getEmail(), mockUserDto.getEmail());
    }

    @Test
    void givenCard_whenLoginMethod_thenReturnCard() {

        Mockito.when(userService.addCard(Mockito.any(),Mockito.any())).thenReturn(mockCard);

        Card response = userController.addCard(1L,mockCard).getBody();

        assertEquals(response.getId(), 1L);
    }


}
