package com.example.springboottutorial.dao.jdbc;

import com.example.springboottutorial.dao.AuthorDao;
import com.example.springboottutorial.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthorDaoJdbcImpl implements AuthorDao {

    private final DataSource dataSource;

    public AuthorDaoJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Author> getById(UUID id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE id=?")) {

            preparedStatement.setString(1, id.toString());
            try (var resultSet = preparedStatement.executeQuery()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> getByFirstName(String firstName) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE first_name=?")) {
            preparedStatement.setString(1, firstName);
            try (var resultSet = preparedStatement.executeQuery()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> save(Author author) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("INSERT INTO author (id, first_name, last_name) values (?, ?, ?)")) {
            var id = UUID.randomUUID();
            preparedStatement.setObject(1, id, Types.OTHER);
            preparedStatement.setString(2, author.getFirstName());
            preparedStatement.setString(3, author.getLastName());
            preparedStatement.execute();
            return getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> update(Author author, UUID id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("UPDATE author set id=?, first_name=?, last_name=? WHERE id=?")) {
            preparedStatement.setString(1, id.toString());
            preparedStatement.setString(2, author.getFirstName());
            preparedStatement.setString(3, author.getLastName());
            preparedStatement.setString(4, id.toString());
            preparedStatement.execute();
            return getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM author WHERE id=?")) {
            preparedStatement.setString(1, id.toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Author> getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            var author = new Author();
            author.setId(UUID.fromString(resultSet.getString("id")));
            author.setFirstName(resultSet.getString("first_name"));
            author.setLastName(resultSet.getString("last_name"));
            return Optional.of(author);
        }
        return Optional.empty();
    }
}
