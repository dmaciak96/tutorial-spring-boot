package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);
}
