package com.example.springboottutorial.model;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    @Override
    public String getName() {
        return "I am cricket coach";
    }
}
