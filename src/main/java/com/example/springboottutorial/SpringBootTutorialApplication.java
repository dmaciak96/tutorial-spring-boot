package com.example.springboottutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * We need to specify additional package for component scan, because
 * Spring boot component scan package default is the same package as class annotated with
 * @SpringBootApplication annotation
 */
@SpringBootApplication(scanBasePackages = {
        "com.example.util",
        "com.example.springboottutorial"})
public class SpringBootTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTutorialApplication.class, args);
    }

}
