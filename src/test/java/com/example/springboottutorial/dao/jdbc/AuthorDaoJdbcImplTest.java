package com.example.springboottutorial.dao.jdbc;

import com.example.springboottutorial.dao.AuthorDao;
import com.example.springboottutorial.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ComponentScan(basePackages = {"com.example.springboottutorial.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoJdbcImplTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetById() {
        var createdAuthor = authorDao.save(new Author("Carl", "Johnson"));
        var result = authorDao.getById(createdAuthor.get().getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getFirstName()).isEqualTo("Carl");
        assertThat(result.get().getLastName()).isEqualTo("Johnson");
    }

    @Test
    void testGetByFirstName() {
        var createdAuthor = authorDao.save(new Author("Carl", "Johnson"));
        var result = authorDao.getByFirstName("Carl");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getFirstName()).isEqualTo("Carl");
        assertThat(result.get().getLastName()).isEqualTo("Johnson");
    }

    @Test
    void testSave() {
        var result = authorDao.save(new Author("Carl", "Johnson"));
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isNotNull();
        assertThat(result.get().getFirstName()).isEqualTo("Carl");
        assertThat(result.get().getLastName()).isEqualTo("Johnson");
    }

    @Test
    void testDelete() {
        var result = authorDao.save(new Author("Carl", "Johnson"));
        authorDao.delete(result.get().getId());
        assertThat(authorDao.getById(result.get().getId()).isEmpty()).isTrue();
    }

    @Test
    void testUpdate() {
        var createdAuthor = authorDao.save(new Author("Carl", "Johnson"));
        var updated = authorDao.update(new Author("Jan", "Kowalski"), createdAuthor.get().getId());
        var result = authorDao.getById(updated.get().getId());
        assertThat(result).isEqualTo(updated);
    }
}