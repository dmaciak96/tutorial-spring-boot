package com.example.springboottutorial.dao.hibernate;

import com.example.springboottutorial.dao.AuthorDao;
import com.example.springboottutorial.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuthorDaoHibernateImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoHibernateImpl(EntityManagerFactory entityManager) {
        this.entityManagerFactory = entityManager;
    }

    @Override
    public Optional<Author> getById(UUID id) {
        return Optional.ofNullable(getEntityManager()
                .find(Author.class, id));
    }

    @Override
    public Optional<Author> getByFirstName(String firstName) {
        var query = getEntityManager().createQuery(
                "SELECT a from Author a WHERE a.firstName=:firstName", Author.class);
        query.setParameter("firstName", firstName);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Author> save(Author author) {
        var em = getEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
        return Optional.ofNullable(author);
    }

    @Override
    public Optional<Author> update(Author author, UUID id) {
        author.setId(id);
        EntityManager em = getEntityManager();
        em.joinTransaction();
        em.merge(author);
        em.flush();
        em.clear();
        return Optional.of(em.find(Author.class, author.getId()));
    }

    @Override
    public void delete(UUID id) {
        var em = getEntityManager();
        em.getTransaction().begin();
        var toDelete = em.find(Author.class, id);
        em.remove(toDelete);
        em.flush();
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
