package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void shouldSuccessfullySaveAuthor() {
        var author = authorRepository.save(new Author("Test", "Author"));
        assertThat(author).isNotNull();
        assertThat(author.getFirstName()).isEqualTo("Test");
        assertThat(author.getLastName()).isEqualTo("Author");
        assertThat(author.getId()).isNotNull();
    }

    @Test
    void shouldSuccessfullyGetAuthorByUuid() {
        var author = authorRepository.save(new Author("Test", "Author"));
        var result = authorRepository.findById(author.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(author);
    }
}
