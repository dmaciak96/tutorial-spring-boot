package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Author;
import com.example.springboottutorial.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void shouldSuccessfullySaveBook() {
        var book = bookRepository.save(new Book("test", "isbn", "publisher", null));
        assertThat(book).isNotNull();
        assertThat(book.getId()).isNotNull();
        assertThat(book.getTitle()).isEqualTo("test");
        assertThat(book.getIsbn()).isEqualTo("isbn");
        assertThat(book.getPublisher()).isEqualTo("publisher");
        assertThat(book.getAuthorId()).isNull();
    }

    @Test
    void shouldSuccessfullyGetBookByUuid() {
        var book = bookRepository.save(new Book("test", "isbn", "publisher", null));
        var result = bookRepository.findById(book.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(book);
    }
}
