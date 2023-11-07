package com.example.springboottutorial.controller;

import com.example.springboottutorial.controller.coach.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController {

    private static final Logger LOG = LoggerFactory.getLogger(CoachController.class);

    private final Coach coach;

    public CoachController(Coach coach) {
        LOG.warn("Creating Spring Bean: {}", this.getClass().getSimpleName());
        this.coach = coach;
    }

    @GetMapping("/")
    public String getCoachName() {
        return coach.getName();
    }
}
