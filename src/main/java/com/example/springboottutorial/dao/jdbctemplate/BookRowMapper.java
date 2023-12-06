package com.example.springboottutorial.dao.jdbctemplate;

import com.example.springboottutorial.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var book = new Book();
        book.setId(UUID.fromString(resultSet.getString("id")));
        var authorId = resultSet.getString("author_id");
        book.setAuthorId(authorId == null ? null : UUID.fromString(authorId));
        book.setTitle(resultSet.getString("title"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPublisher(resultSet.getString("publisher"));
        return book;
    }
}
