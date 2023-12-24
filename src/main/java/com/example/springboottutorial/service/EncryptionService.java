package com.example.springboottutorial.service;

public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);
}
