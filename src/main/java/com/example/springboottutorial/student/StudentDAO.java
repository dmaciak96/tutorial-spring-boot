package com.example.springboottutorial.student;

import java.util.List;

public interface StudentDAO {
    void save(StudentEntity studentEntity);

    void update(StudentEntity source, int id);

    void delete(int id);

    StudentEntity findById(int id);

    List<StudentEntity> findAll();

    List<StudentEntity> findByLastName(String lastName);
}
