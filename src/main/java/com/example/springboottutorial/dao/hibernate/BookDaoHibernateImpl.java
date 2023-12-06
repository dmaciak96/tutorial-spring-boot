package com.example.springboottutorial.dao.hibernate;

import com.example.springboottutorial.dao.BookDao;
import com.example.springboottutorial.domain.Book;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class BookDaoHibernateImpl implements BookDao {

    private final EntityManager entityManager;

    public BookDaoHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Book> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Book book, UUID id) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }
}
