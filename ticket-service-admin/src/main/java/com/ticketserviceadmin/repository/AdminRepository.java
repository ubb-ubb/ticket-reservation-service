package com.ticketserviceadmin.repository;

import com.ticketserviceadmin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Boolean existsByEmail(String email);

    Admin findByEmail(String email);
}
