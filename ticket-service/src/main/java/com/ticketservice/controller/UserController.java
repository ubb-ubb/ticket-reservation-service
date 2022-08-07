package com.ticketservice.controller;

import com.ticketservice.client.PaymentClient;
import com.ticketservice.dto.UserDto;
import com.ticketservice.model.Card;
import com.ticketservice.model.User;
import com.ticketservice.producer.NotificationProducer;
import com.ticketservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(value = "/signup")
    public ResponseEntity<UserDto> register (@RequestBody @Valid UserDto request) {

        return ResponseEntity.ok()
                                .body(userService.register(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> login (@RequestBody UserDto request) {


        return ResponseEntity.ok()
                .body(userService.login(request));
    }

    @PostMapping(value = "/{userId}/cards")
    public ResponseEntity<Card> addCard (@PathVariable Long userId, @RequestBody Card request) {


        return ResponseEntity.ok()
                .body(userService.addCard(request,userId));
    }

}
