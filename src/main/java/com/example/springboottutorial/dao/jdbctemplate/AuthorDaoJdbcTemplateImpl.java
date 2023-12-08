package com.example.springboottutorial.dao.jdbctemplate;

import com.example.springboottutorial.dao.AuthorDao;
import com.example.springboottutorial.domain.Author;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuthorDaoJdbcTemplateImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public List<Author> listAuthorByLastName(String lastName) {
        return null;
    }

    @Override
    public Optional<Author> getById(UUID id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM author WHERE id=?",
                getRowMapper(), id.toString()));
    }

    @Override
    public Optional<Author> getByFirstName(String firstName) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name=?",
                getRowMapper(), firstName));
    }

    @Override
    public Optional<Author> save(Author author) {
        var newId = UUID.randomUUID();
        jdbcTemplate.update("INSERT INTO author(id, first_name, last_name) VALUES (?, ?, ?)",
                newId.toString(), author.getFirstName(), author.getLastName());
        return getById(newId);
    }

    @Override
    public Optional<Author> update(Author author, UUID id) {
        jdbcTemplate.update("UPDATE author SET first_name=?, last_name=? WHERE id=?",
                author.getFirstName(), author.getLastName(), id.toString());
        return getById(id);
    }

    @Override
    public void delete(UUID id) {
        jdbcTemplate.update("DELETE  FROM author WHERE id=?", id.toString());
    }

    private RowMapper<Author> getRowMapper() {
        return new AuthorRowMapper();
    }
}
