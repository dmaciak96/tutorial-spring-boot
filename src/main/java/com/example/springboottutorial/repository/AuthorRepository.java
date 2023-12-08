package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Author;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
  Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

  @Async
  Future<Author> queryByFirstNameAndLastName(String firstName, String lastName);
}
