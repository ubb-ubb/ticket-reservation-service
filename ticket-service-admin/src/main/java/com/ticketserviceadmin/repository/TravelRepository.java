package com.ticketserviceadmin.repository;

import com.ticketserviceadmin.model.Admin;
import com.ticketserviceadmin.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    List<Travel> findByAdmin(Admin admin);
    List<Travel> findByAdmin_Id(Long id);


}
