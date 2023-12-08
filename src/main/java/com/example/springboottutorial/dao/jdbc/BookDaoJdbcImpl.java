package com.example.springboottutorial.dao.jdbc;

import com.example.springboottutorial.dao.BookDao;
import com.example.springboottutorial.domain.Book;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookDaoJdbcImpl implements BookDao {

    private final DataSource dataSource;

    public BookDaoJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findAll(int pageSize, int offset) {
        return null;
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Book> getById(UUID id) {
        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement("SELECT * FROM book WHERE id=?")) {
            ps.setString(1, id.toString());
            try (var rs = ps.executeQuery()) {
                return getBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement("SELECT * FROM book WHERE title=?")) {
            ps.setString(1, title);
            try (var rs = ps.executeQuery()) {
                return getBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> save(Book book) {
        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement("INSERT INTO book(id, title, isbn, publisher, author_id) VALUES (?, ?, ?, ?, ?)")) {
            var id = UUID.randomUUID();
            ps.setString(1, id.toString());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getPublisher());
            ps.setString(5, book.getAuthorId() == null ? null : book.getAuthorId().toString());
            ps.execute();
            return getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> update(Book book, UUID id) {
        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement("UPDATE book SET title=?, publisher=?, isbn=?, author_id=? WHERE id=?")) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getPublisher());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getAuthorId() == null ? null : book.getAuthorId().toString());
            ps.setString(5, id.toString());
            ps.execute();
            return getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement("DELETE FROM book WHERE id=?")) {
            ps.setString(1, id.toString());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Book> getBookFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            var book = new Book();
            book.setId(UUID.fromString(resultSet.getString("id")));
            var authorId = resultSet.getString("author_id");
            book.setAuthorId(authorId == null ? null : UUID.fromString(authorId));
            book.setTitle(resultSet.getString("title"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setPublisher(resultSet.getString("publisher"));
            return Optional.of(book);
        }
        return Optional.empty();
    }
}
