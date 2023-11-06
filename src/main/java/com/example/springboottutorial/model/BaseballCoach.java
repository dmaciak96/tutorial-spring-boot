package com.example.springboottutorial.model;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{
    @Override
    public String getName() {
        return "I am baseball coach";
    }
}
