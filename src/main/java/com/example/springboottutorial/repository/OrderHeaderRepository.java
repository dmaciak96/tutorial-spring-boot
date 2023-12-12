package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Customer;
import com.example.springboottutorial.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, UUID> {
    Set<OrderHeader> findAllByCustomer(Customer customer);
}
