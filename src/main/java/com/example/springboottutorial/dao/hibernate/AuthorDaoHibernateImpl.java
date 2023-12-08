package com.example.springboottutorial.dao.hibernate;

import com.example.springboottutorial.dao.AuthorDao;
import com.example.springboottutorial.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoHibernateImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoHibernateImpl(EntityManagerFactory entityManager) {
        this.entityManagerFactory = entityManager;
    }

    @Override
    public List<Author> listAuthorByLastName(String lastName) {
        try(var em = getEntityManager()) {
            var query = em.createQuery("SELECT a FROM Author a WHERE a.lastName LIKE :lastName");
            query.setParameter("lastName", lastName + "%");
          return (List<Author>) query.getResultList();
        }
    }

    public List<Author> listAuthorByLastNameTypedQuery(String lastName) {
        try(var em = getEntityManager()) {
            var typedQuery = em.createQuery("SELECT a FROM Author a WHERE a.lastName LIKE :lastName", Author.class);
            typedQuery.setParameter("lastName", lastName + "%");
            return  typedQuery.getResultList();
        }
    }

    public Optional<Author> getByName(String firstName, String lastName) {
        try(var em = getEntityManager()) {
            var query = em.createNamedQuery("author_find_by_name", Author.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            return Optional.ofNullable(query.getSingleResult());
        }
    }

    public Optional<Author> getByNameCriteriaQuery(String firstName, String lastName) {
        try(var em = getEntityManager()) {
            var criteriaBuilder = em.getCriteriaBuilder();
            var firstNameParam = criteriaBuilder.parameter(String.class);
            var lastNameParam = criteriaBuilder.parameter(String.class);
            var criteriaQuery = criteriaBuilder.createQuery(Author.class);

            var root = criteriaQuery.from(Author.class);
            var firstNamePredicate = criteriaBuilder.equal(root.get("firstName"), firstNameParam);
            var lastNamePredicate = criteriaBuilder.equal(root.get("lastName"), lastNameParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePredicate, lastNamePredicate));

            var typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setParameter(firstNameParam, firstName);
            typedQuery.setParameter(lastNameParam, lastName);
            return Optional.ofNullable(typedQuery.getSingleResult());
        }
    }

    public Optional<Author> getByNameNativeSqlQuery(String firstName, String lastName) {
        try(var em = getEntityManager()) {
            var query = em.createNativeQuery("SELECT * FROM author a WHERE a.first_name = ? and a.last_name = ?", Author.class);
            query.setParameter(1, firstName);
            query.setParameter(2, lastName);
            return Optional.ofNullable((Author) query.getSingleResult());
        }
    }

    @Override
    public List<Author> findAll() {
        try(var em = getEntityManager()) {
            var namedQuery = em.createNamedQuery("author_find_all", Author.class);
            return  namedQuery.getResultList();
        }
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
