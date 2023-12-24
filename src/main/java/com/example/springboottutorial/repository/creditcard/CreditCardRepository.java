package com.example.springboottutorial.repository.creditcard;

import com.example.springboottutorial.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
