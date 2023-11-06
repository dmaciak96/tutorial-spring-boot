package com.example.springboottutorial.controller;

import com.example.springboottutorial.model.Coach;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QualifierController {
    private static final String BASEBALL_COACH_BEEN_ID = "baseballCoach";
    private static final String CRICKET_COACH_BEEN_ID = "cricketCoach";
    private static final String TENNIS_COACH_BEEN_ID = "tennisCoach";
    private static final String TRACK_COACH_BEEN_ID = "trackCoach";

    private final Coach coach;

    public QualifierController(@Qualifier(TENNIS_COACH_BEEN_ID) Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/")
    public String getCoachName() {
        return coach.getName();
    }
}
