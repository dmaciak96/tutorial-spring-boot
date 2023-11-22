package com.example.springboottutorial;

import com.example.springboottutorial.student.StudentDAO;
import com.example.springboottutorial.student.StudentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootTutorialApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootTutorialApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTutorialApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            createStudents(studentDAO);
        };
    }

    private void createStudents(StudentDAO studentDAO) {
        log.info("Creating new student objects...");
        var students = List.of(new StudentEntity("Carl", "Johnson", "cj2005@gmail.com"),
                new StudentEntity("Sweet", "Johnson", "sj2005@gmail.com"),
                new StudentEntity("Big", "Smoke", "bs2005@gmail.com"));
        log.info("Saving the students...");
        students.forEach(student -> {
            log.info("Saved student {} {}", student.getFirstName(), student.getLastName());
            studentDAO.save(student);
        });
    }

    private void createStudent(StudentDAO studentDAO) {
        log.info("Creating new student object...");
        var student = new StudentEntity("Carl", "Johnson", "cj2005@gmail.com");
        log.info("Saving the student...");
        studentDAO.save(student);
        log.info("Saved student. Generated id: {}", student.getId());
    }
}
