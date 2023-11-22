package com.example.springboottutorial.student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    void save(StudentEntity studentEntity);

    Optional<StudentEntity> findById(int id);

    List<StudentEntity> findAll();

    List<StudentEntity> findByLastName(String lastName);
}
