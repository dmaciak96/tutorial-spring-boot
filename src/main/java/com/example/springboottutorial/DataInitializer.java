package com.example.springboottutorial;

import com.example.springboottutorial.domain.Book;
import com.example.springboottutorial.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            log.info("Creating books...");
            var bookOne = new Book(0, "Python. Instrukcje dla programisty. Wydanie III", "9788328904316", "Helion", null);
            var bookTwo = new Book(1, "Czysty kod. Podręcznik dobrego programisty", "9788383223452", "Helion", null);
            var bookThree = new Book(2, "Java. Rusz głową! Wydanie III", "9788328399853", "Helion", null);
            bookRepository.saveAll(List.of(bookOne, bookTwo, bookThree));
        }
    }
}
