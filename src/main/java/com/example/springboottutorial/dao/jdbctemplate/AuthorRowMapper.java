package com.example.springboottutorial.dao.jdbctemplate;

import com.example.springboottutorial.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        var author = new Author(rs.getString("first_name"), rs.getString("last_name"));
        author.setId(UUID.fromString(rs.getString("id")));
        return author;
    }
}
