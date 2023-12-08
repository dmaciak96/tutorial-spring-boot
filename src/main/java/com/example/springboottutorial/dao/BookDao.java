package com.example.springboottutorial.dao;

import com.example.springboottutorial.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookDao {

    List<Book> findAll();

    Optional<Book> getById(UUID id);

    Optional<Book> getByTitle(String title);

    Optional<Book> save(Book book);

    Optional<Book> update(Book book, UUID id);

    void delete(UUID id);
}
