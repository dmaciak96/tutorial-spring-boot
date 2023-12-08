package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

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

    @Test
    void testGetById() {
        var optionalBook = bookRepository.save(new Book("title", "isbn", "publisher", null));
        var result = bookRepository.getById(optionalBook.getId());
        assertThat(result.getTitle()).isEqualTo("title");
        assertThat(result.getIsbn()).isEqualTo("isbn");
        assertThat(result.getPublisher()).isEqualTo("publisher");
    }

    @Test
    void testGetByTitle() {
        var optionalBook = bookRepository.save(new Book("title", "isbn", "publisher", null));
        var result = bookRepository.findByTitle("title");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getTitle()).isEqualTo("title");
        assertThat(result.get().getIsbn()).isEqualTo("isbn");
        assertThat(result.get().getPublisher()).isEqualTo("publisher");
    }

    @Test
    void testDelete() {
        var result = bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.deleteById(result.getId());
        assertThat(bookRepository.findById(result.getId()).isEmpty()).isTrue();
    }

    @Test
    void testUpdate() {
        var created = bookRepository.save(new Book("title", "isbn", "publisher", null));
        created.setPublisher("publisher2");
        created.setTitle("title2");
        created.setIsbn("isbn2");
        var updated = bookRepository.save(created);
        var result = bookRepository.findById(updated.getId());
        assertThat(result.get().getTitle()).isEqualTo(updated.getTitle());
        assertThat(result.get().getIsbn()).isEqualTo(updated.getIsbn());
        assertThat(result.get().getPublisher()).isEqualTo(updated.getPublisher());
    }

    @Test
    public void testFindAll() {
        var first = bookRepository.save(new Book("title", "isbn", "publisher", null));
        var second = bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        var result = bookRepository.findAll();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(first.getId());
        assertThat(result.get(1).getId()).isEqualTo(second.getId());
    }

    @Test
    public void testFindAllPage1Pageable() {
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        var result = bookRepository.findAll(Pageable.ofSize(10).withPage(0));
        assertThat(result).hasSize(10);
    }

    @Test
    public void testFindAllPage2Pageable() {
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        var result = bookRepository.findAll(Pageable.ofSize(10).withPage(1));
        assertThat(result).hasSize(2);
    }

    @Test
    public void testFindAllPage3Pageable() {
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        bookRepository.save(new Book("title", "isbn", "publisher", null));
        bookRepository.save(new Book("title2", "isbn2", "publisher2", null));
        var result = bookRepository.findAll(Pageable.ofSize(10).withPage(2));
        assertThat(result).hasSize(0);
    }
}
