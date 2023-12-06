package com.example.springboottutorial.dao.jdbctemplate;

import com.example.springboottutorial.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = {"com.example.springboottutorial.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoJdbcTemplateImplTest {

    @Autowired
    BookDaoJdbcTemplateImpl bookDao;

    @Test
    void testGetById() {
        var optionalBook = bookDao.save(new Book("title", "isbn", "publisher", null));
        var result = bookDao.getById(optionalBook.get().getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getTitle()).isEqualTo("title");
        assertThat(result.get().getIsbn()).isEqualTo("isbn");
        assertThat(result.get().getPublisher()).isEqualTo("publisher");
    }

    @Test
    void testGetByTitle() {
        var optionalBook = bookDao.save(new Book("title", "isbn", "publisher", null));
        var result = bookDao.getByTitle("title");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getTitle()).isEqualTo("title");
        assertThat(result.get().getIsbn()).isEqualTo("isbn");
        assertThat(result.get().getPublisher()).isEqualTo("publisher");
    }

    @Test
    void testSave() {
        var result = bookDao.save(new Book("title", "isbn", "publisher", null));
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isNotNull();
        assertThat(result.get().getTitle()).isEqualTo("title");
        assertThat(result.get().getIsbn()).isEqualTo("isbn");
        assertThat(result.get().getPublisher()).isEqualTo("publisher");
    }

    @Test
    void testDelete() {
        var result = bookDao.save(new Book("title", "isbn", "publisher", null));
        bookDao.delete(result.get().getId());
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(result.get().getId()));
    }

    @Test
    void testUpdate() {
        var optionalBook = bookDao.save(new Book("title", "isbn", "publisher", null));
        var updated = bookDao.update(new Book("title2", "isbn2", "publisher2", null), optionalBook.get().getId());
        var result = bookDao.getById(updated.get().getId());
        assertThat(result.get().getTitle()).isEqualTo(updated.get().getTitle());
        assertThat(result.get().getIsbn()).isEqualTo(updated.get().getIsbn());
        assertThat(result.get().getPublisher()).isEqualTo(updated.get().getPublisher());
    }
}