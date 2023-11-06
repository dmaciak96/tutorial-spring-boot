package com.example.springboottutorial.model;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
    @Override
    public String getName() {
        return "I am track coach";
    }
}
