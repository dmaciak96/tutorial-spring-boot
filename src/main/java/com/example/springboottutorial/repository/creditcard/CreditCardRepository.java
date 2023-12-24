package com.example.springboottutorial.repository.creditcard;

import com.example.springboottutorial.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
