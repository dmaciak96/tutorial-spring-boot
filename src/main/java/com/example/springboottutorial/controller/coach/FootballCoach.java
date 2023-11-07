package com.example.springboottutorial.controller.coach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
    private static final Logger LOG = LoggerFactory.getLogger(FootballCoach.class);

    public FootballCoach() {
        LOG.warn("Creating Spring Bean: {}", this.getClass().getSimpleName());
    }

    @Override
    public String getName() {
        return "I am football coach";
    }
}
