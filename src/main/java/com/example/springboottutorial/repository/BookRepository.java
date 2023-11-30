package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
