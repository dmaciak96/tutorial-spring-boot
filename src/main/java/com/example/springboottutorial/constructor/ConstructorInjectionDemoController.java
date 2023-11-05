package com.example.springboottutorial.constructor;

import com.example.springboottutorial.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructorInjectionDemoController {

    private final Coach coach;

    @Autowired //This annotation is required if we have more than one constructor
    public ConstructorInjectionDemoController(Coach Coach) {
        this.coach = Coach;
    }

    @GetMapping("/constructor-di")
    public String getName() {
        return "%s - inject by constructor".formatted(coach.getName());
    }
}
