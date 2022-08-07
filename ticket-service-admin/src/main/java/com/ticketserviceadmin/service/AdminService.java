package com.ticketserviceadmin.service;

import com.google.common.hash.Hashing;
import com.ticketserviceadmin.model.Admin;
import com.ticketserviceadmin.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Admin register(Admin request) {

        request.setPassword(Hashing.sha256().hashString(request.getPassword(), StandardCharsets.UTF_8).toString());

        return adminRepository.save(request);
    }


    public Admin login(Admin request) {

        request.setPassword(Hashing.sha256().hashString(request.getPassword(), StandardCharsets.UTF_8).toString());

        Admin adminToCheck = adminRepository.findByEmail(request.getEmail());

        if (adminToCheck == null) {
            log.info ("Login failed");
            return request;
        }

        if (adminToCheck.getPassword().equals(request.getPassword()) && adminToCheck.getEmail().equalsIgnoreCase(request.getEmail()) ) {
            log.info("Login success");
        }
        else {
            log.info ("Login failed");
        }
        return request;
    }



}
