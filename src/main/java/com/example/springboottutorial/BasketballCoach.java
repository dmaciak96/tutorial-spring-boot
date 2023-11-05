package com.example.springboottutorial;

import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach {

    @Override
    public String getName() {
        return BasketballCoach.class.getSimpleName();
    }
}
