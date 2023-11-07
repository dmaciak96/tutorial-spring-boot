package com.example.springboottutorial.controller.coach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class TennisCoach implements Coach {
    private static final Logger LOG = LoggerFactory.getLogger(TennisCoach.class);

    public TennisCoach() {
        LOG.warn("Creating Spring Bean: {}", this.getClass().getSimpleName());
    }

    @Override
    public String getName() {
        return "I am tennis coach";
    }
}
