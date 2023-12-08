package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    @Async
    Future<Author> queryByFirstNameAndLastName(String firstName, String lastName);

    Page<Author> findAuthorByLastName(String lastName, Pageable pageable);
}
