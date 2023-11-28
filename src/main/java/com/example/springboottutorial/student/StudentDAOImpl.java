package com.example.springboottutorial.student;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(StudentEntity studentEntity) {
        entityManager.persist(studentEntity);
    }

    @Override
    @Transactional
    public void update(StudentEntity source, int id) {
        var studentToUpdate = entityManager.find(StudentEntity.class, id);
        if (studentToUpdate == null) {
            throw new StudentNotFoundException("Student not found by id " + id);
        }

        studentToUpdate.setEmail(source.getEmail());
        studentToUpdate.setFirstName(source.getFirstName());
        studentToUpdate.setLastName(source.getLastName());
        entityManager.merge(studentToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        var studentToDelete = entityManager.find(StudentEntity.class, id);
        if (studentToDelete == null) {
            throw new StudentNotFoundException("Student not found by id " + id);
        }
        entityManager.remove(studentToDelete);
    }

    @Override
    public StudentEntity findById(int id) {
        var student = entityManager.find(StudentEntity.class, id);
        if (student == null) {
            throw new StudentNotFoundException("Student not found by id " + id);
        }
        return student;
    }

    @Override
    public List<StudentEntity> findAll() {
        return entityManager.createQuery("FROM StudentEntity order by lastName", StudentEntity.class)
                .getResultList();
    }

    @Override
    public List<StudentEntity> findByLastName(String lastName) {
        var query = entityManager.createQuery("FROM StudentEntity where lastName=:theLastname", StudentEntity.class);
        query.setParameter("theLastname", lastName);
        return query.getResultList();
    }
}
