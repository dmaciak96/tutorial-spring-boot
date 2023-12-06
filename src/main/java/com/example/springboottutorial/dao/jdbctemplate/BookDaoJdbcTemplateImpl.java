package com.example.springboottutorial.dao.jdbctemplate;

import com.example.springboottutorial.dao.BookDao;
import com.example.springboottutorial.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class BookDaoJdbcTemplateImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> getById(UUID id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM book WHERE id=?", getRowMapper(), id.toString()));
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM book WHERE title=?", getRowMapper(), title));
    }

    @Override
    public Optional<Book> save(Book book) {
        var id = UUID.randomUUID();
        jdbcTemplate.update("INSERT INTO book(id, title, isbn, publisher, author_id) VALUES (?, ?, ?, ?, ?)",
                id.toString(), book.getTitle(), book.getIsbn(), book.getPublisher(), book.getAuthorId());
        return getById(id);
    }

    @Override
    public Optional<Book> update(Book book, UUID id) {
        jdbcTemplate.update("UPDATE book SET title=?, publisher=?, isbn=?, author_id=? WHERE id=?",
                book.getTitle(), book.getPublisher(), book.getIsbn(), book.getAuthorId(), id.toString());
        return getById(id);
    }

    @Override
    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id.toString());
    }

    private RowMapper<Book> getRowMapper() {
        return new BookRowMapper();
    }
}
