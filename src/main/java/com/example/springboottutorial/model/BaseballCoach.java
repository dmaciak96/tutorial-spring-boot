package com.example.springboottutorial.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class BaseballCoach implements Coach{
    @Override
    public String getName() {
        return "I am baseball coach";
    }
}
