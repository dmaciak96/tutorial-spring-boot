package com.example.springboottutorial.repository.pan;

import com.example.springboottutorial.domain.pan.CreditCardPAN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {
}
