package com.example.springboottutorial.repository.cardholder;

import com.example.springboottutorial.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}
