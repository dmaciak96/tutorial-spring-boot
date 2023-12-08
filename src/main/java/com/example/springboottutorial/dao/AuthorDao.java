package com.example.springboottutorial.dao;

import com.example.springboottutorial.domain.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorDao {

    List<Author> findAll();

    List<Author> listAuthorByLastName(String lastName);

    Optional<Author> getById(UUID id);

    Optional<Author> getByFirstName(String firstName);

    Optional<Author> save(Author author);

    Optional<Author> update(Author author, UUID id);

    void delete(UUID id);
}
