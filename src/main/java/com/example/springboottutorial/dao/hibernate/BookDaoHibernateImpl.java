package com.example.springboottutorial.dao.hibernate;

import com.example.springboottutorial.dao.BookDao;
import com.example.springboottutorial.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookDaoHibernateImpl implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public BookDaoHibernateImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Book> findAll() {
        try (var em = getEntityManager()) {
            var query = em.createNamedQuery("find_all", Book.class);
            return query.getResultList();
        }
    }

    @Override
    public List<Book> findAll(int pageSize, int offset) {
        return null;
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        try (var em = getEntityManager()) {
            var query = em.createQuery("SELECT b FROM  Book b", Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }
    }

    @Override
    public Optional<Book> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        try (var em = getEntityManager()) {
            var query = em.createNamedQuery("find_by_title", Book.class);
            query.setParameter("title", title);
            return Optional.ofNullable(query.getSingleResult());
        }
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

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
