package com.example.springboottutorial.student;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<StudentEntity> findById(int id) {
        return Optional.ofNullable(entityManager.find(StudentEntity.class, id));
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
