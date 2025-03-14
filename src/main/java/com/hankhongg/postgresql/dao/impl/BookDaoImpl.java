package com.hankhongg.postgresql.dao.impl;

import com.hankhongg.postgresql.dao.BookDao;
import com.hankhongg.postgresql.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;
    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Book book){
        String sql = "insert into books values (?,?,?)";
        jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getAuthorId());
    }

    public Optional<Book> findOne(String isbn) {
        String sql = "select isbn, title, author_id from books where isbn = ? limit 1";
        List<Book> results = jdbcTemplate.query(sql, new BookRowMapper(), isbn);
        return results.stream().findFirst();
    }
    public List<Book> findAll() {
        return jdbcTemplate.query("select isbn, title, author_id from books", new BookRowMapper());
    }
    public void update(String isbn, Book book) {
        jdbcTemplate.update("update books set isbn = ?, title = ?, author_id = ? where isbn = ?",
                book.getIsbn(), book.getTitle(),book.getAuthorId(), isbn);
    }
    public void delete(String isbn) {
        jdbcTemplate.update("delete from books where isbn = ?", isbn);
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder().isbn(rs.getString("isbn")).title(rs.getString("title")).authorId(rs.getLong("author_id")).build();
        }
    }
}
