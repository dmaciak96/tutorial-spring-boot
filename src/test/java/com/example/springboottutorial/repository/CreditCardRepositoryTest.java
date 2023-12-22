package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.CreditCard;
import com.example.springboottutorial.service.EncryptionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {
    private static final String NUMBER = "12345678900000";
    private static final String CCV = "666";
    private static final String DATE = "12/55";


    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EncryptionService encryptionService;


    @Test
    void testSave() {
        var saved = saveTestData();

        var fetched = creditCardRepository.findById(saved.getId());
        assertThat(fetched.isPresent()).isTrue();
        assertThat(fetched.get().getCreditCardNumber()).isEqualTo(NUMBER);
        assertThat(fetched.get().getCcv()).isEqualTo(CCV);
        assertThat(fetched.get().getExpirationDate()).isEqualTo(DATE);
    }


    private CreditCard saveTestData() {
        var creditCard = new CreditCard();
        creditCard.setCreditCardNumber(NUMBER);
        creditCard.setCcv(CCV);
        creditCard.setExpirationDate(DATE);
        return creditCardRepository.save(creditCard);
    }

    @AfterEach
    void deleteAll() {
        creditCardRepository.deleteAll();
    }
}