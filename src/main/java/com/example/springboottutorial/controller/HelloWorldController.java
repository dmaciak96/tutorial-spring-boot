package com.example.springboottutorial.controller;

import com.example.util.HelloWorldComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloWorldController {

    public Optional<HelloWorldComponent> helloWorldComponent;

    public HelloWorldController(Optional<HelloWorldComponent> helloWorldComponent) {
        this.helloWorldComponent = helloWorldComponent;
    }

    @GetMapping("/")
    public String helloWorld() {
        if (helloWorldComponent.isEmpty()) {
            return "Hello world component not found in Spring Container";
        }
        return helloWorldComponent.get().getName();
    }
}
