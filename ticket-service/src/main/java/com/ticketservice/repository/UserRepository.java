package com.ticketservice.repository;

import com.ticketservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByEmailIgnoreCase(String email);
    Boolean existsByUsernameIgnoreCase(String username);
    User findByUsername(String username);

}
