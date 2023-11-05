package com.example.springboottutorial.field;

import com.example.springboottutorial.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldInjectionDemoController {

    @Autowired
    private Coach coach;

    @GetMapping("field-di")
    public String getName() {
        return "%s - inject by field".formatted(coach.getName());
    }
}
