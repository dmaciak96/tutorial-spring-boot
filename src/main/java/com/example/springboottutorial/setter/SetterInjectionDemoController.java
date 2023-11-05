package com.example.springboottutorial.setter;

import com.example.springboottutorial.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SetterInjectionDemoController {

    private Coach coach;


    /**
     * This setter will be invoked after controller instance creation
     * To create instance without coach dependency, we need to declare Optional<Coach> as parameter
     */
    @Autowired
    public void setBasketballCoach(Optional<Coach> coach) {
        this.coach = coach.orElse(null);
    }

    @GetMapping("setter-di")
    public String getName() {
        if (coach == null) {
            return "No Coach found in Spring Container";
        }
        return "%s - inject by setter".formatted(coach.getName());
    }
}
