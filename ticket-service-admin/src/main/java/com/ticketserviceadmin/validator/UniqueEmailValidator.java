package com.ticketserviceadmin.validator;


import com.ticketserviceadmin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !adminRepository.existsByEmail(email);
    }
}