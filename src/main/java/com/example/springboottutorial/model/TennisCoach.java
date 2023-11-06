package com.example.springboottutorial.model;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getName() {
        return "I am tennis coach";
    }
}
