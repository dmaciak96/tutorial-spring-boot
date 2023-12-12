package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, UUID> {
}
