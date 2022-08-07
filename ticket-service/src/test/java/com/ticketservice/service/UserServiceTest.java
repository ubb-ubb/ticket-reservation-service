package com.ticketservice.service;

import com.ticketservice.dto.UserDto;
import com.ticketservice.model.Card;
import com.ticketservice.model.User;
import com.ticketservice.producer.NotificationProducer;
import com.ticketservice.repository.CardRepository;
import com.ticketservice.repository.TicketRepository;
import com.ticketservice.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private NotificationProducer notificationProducer;



    User user = new User();
    UserDto userDto = new UserDto();
    Card mockCard = new Card();

    User correctUser = new User();
    User wrongUser = new User();

    @Mock
    private ModelMapper modelMapper;






    @BeforeEach
    void setUp() {

        user.setUsername("User");
        user.setEmail("test@gmail.com");
        user.setPassword("123456789");

        correctUser.setUsername("User");
        correctUser.setEmail("test@gmail.com");
        correctUser.setPassword("123456789");

        wrongUser.setUsername("Wrong User");
        wrongUser.setEmail("test@gmail.com");
        wrongUser.setPassword("123456789");

        userDto.setUsername("User");
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("123456789");

        mockCard.setNo("1234");
        mockCard.setName("Umutcan");

    }

    @Test
    @DisplayName("It should register new user.")
    void givenUserDtoObject_whenRegister_thenReturnSavedUser() {

    Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(user);

    Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

    Mockito.when(notificationProducer.sendToQueue(Mockito.any())).thenReturn(user);

    UserDto response = userService.register(userDto);

    assertEquals(response.getUsername(),"User");
    assertEquals(response.getEmail(), "test@gmail.com");

    verify(userRepository, times(1)).save(Mockito.any());
    verify(modelMapper, times(1)).map(Mockito.any(), Mockito.any());
    }

    @Test
    @DisplayName("It should login user.")
    void givenCorrectUserDtoObject_whenLogin_thenReturnSavedUser() {

        // user
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(user);

        // userToCheck
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(correctUser);

        System.out.println(correctUser);



        UserDto response = userService.login(userDto);
        System.out.println(response);

        assertEquals(response.getUsername(),correctUser.getUsername());
        verify(userRepository,times(1)).findByUsername(Mockito.anyString());


    }

    @Test
    @DisplayName("It should not login user.")
    void givenWrongUserDtoObject_whenLogin_thenReturnSavedUser() {

        // user
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(user);

        // userToCheck
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(wrongUser);


        UserDto response = userService.login(userDto);

        assertNotEquals(response.getUsername(),wrongUser.getUsername());
        verify(userRepository,times(1)).findByUsername(Mockito.anyString());

    }

    @Test
    @DisplayName("It should add card to users card collection")
    void givenCard_whenAddCard_returnCard() {

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        Mockito.when(cardRepository.save(Mockito.any())).thenReturn(mockCard);


        Card response = userService.addCard(mockCard,1L);

        assertEquals(response.getName(), "Umutcan");



    }


}
