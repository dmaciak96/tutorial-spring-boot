package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;


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

    @Test
    void testFindAuthorByLastNamePage() {
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author2"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));
        authorRepository.save(new Author("Test", "Author"));

        var pageOne = authorRepository.findAuthorByLastName("Author2", PageRequest.of(0, 10));
        var pageTwo = authorRepository.findAuthorByLastName("Author2", PageRequest.of(1, 10));
        var pageThree = authorRepository.findAuthorByLastName("Author2", PageRequest.of(2, 10));

        assertThat(pageOne.getTotalElements()).isEqualTo(11);
        assertThat(pageTwo.getTotalElements()).isEqualTo(11);
        assertThat(pageThree.getTotalElements()).isEqualTo(11);
        assertThat(pageOne.getTotalPages()).isEqualTo(2);
        assertThat(pageTwo.getTotalPages()).isEqualTo(2);
        assertThat(pageThree.getTotalPages()).isEqualTo(2);

        assertThat(pageOne.getContent()).hasSize(10);
        assertThat(pageTwo.getContent()).hasSize(1);
        assertThat(pageThree.getContent()).hasSize(0);
    }
}
