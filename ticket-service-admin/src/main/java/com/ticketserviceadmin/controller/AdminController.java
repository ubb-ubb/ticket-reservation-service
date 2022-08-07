package com.ticketserviceadmin.controller;

import com.ticketserviceadmin.model.Admin;
import com.ticketserviceadmin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admins")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/register")
    public ResponseEntity<Admin> register (@RequestBody @Valid Admin request) {

        return ResponseEntity.ok()
                .body(adminService.register(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Admin> login (@RequestBody Admin request) {


        return ResponseEntity.ok()
                .body(adminService.login(request));
    }


}