package com.example.springboottutorial.domain;

import com.example.springboottutorial.service.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return getEncryptionService().encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String attribute) {
        return getEncryptionService().decrypt(attribute);
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
