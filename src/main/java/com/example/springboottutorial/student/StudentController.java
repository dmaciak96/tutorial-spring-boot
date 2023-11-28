package com.example.springboottutorial.student;

import com.example.springboottutorial.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
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

    @GetMapping("/students/{id}")
    public StudentEntity getStudentById(@PathVariable int id) {
        return studentDAO.findById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleStudentNotFoundException(StudentNotFoundException e) {
        var error = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
