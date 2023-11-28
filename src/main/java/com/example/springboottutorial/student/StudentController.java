package com.example.springboottutorial.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping("/students")
    public List<StudentEntity> getAllStudents() {
        return studentDAO.findAll();
    }
}
