package com.example.springboottutorial.student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    void save(StudentEntity studentEntity);

    void update(StudentEntity source, int id);

    void delete(int id);

    Optional<StudentEntity> findById(int id);

    List<StudentEntity> findAll();

    List<StudentEntity> findByLastName(String lastName);
}
