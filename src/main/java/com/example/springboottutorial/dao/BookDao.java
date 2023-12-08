package com.example.springboottutorial.dao;

import com.example.springboottutorial.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookDao {

    List<Book> findAll();

    List<Book> findAll(int pageSize, int offset);

    List<Book> findAll(Pageable pageable);

    Optional<Book> getById(UUID id);

    Optional<Book> getByTitle(String title);

    Optional<Book> save(Book book);

    Optional<Book> update(Book book, UUID id);

    void delete(UUID id);
}
