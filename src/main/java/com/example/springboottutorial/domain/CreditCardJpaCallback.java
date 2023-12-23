package com.example.springboottutorial.domain;

import com.example.springboottutorial.service.EncryptionService;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreditCardJpaCallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard) {
        creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    public void postLoad(CreditCard creditCard) {
        creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
