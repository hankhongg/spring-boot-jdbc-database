package com.hankhongg.postgresql.dao.impl;

import com.hankhongg.postgresql.dao.AuthorDao;
import com.hankhongg.postgresql.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    // as a constructor => pass in set up jbdcTemplate
    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Author author) {
        String sql = "insert into authors (id, name, age) values (?,?,?)";
        jdbcTemplate.update(sql, author.getId(), author.getName(), author.getAge());
    }

    public Optional<Author> findOne(Long id){
        String sql = "select id, name, age from authors where id = ? limit 1";
        List<Author> results = jdbcTemplate.query(sql, new AuthorRowMapper(), id); // query returns a list of result
        return results.stream().findFirst(); // stream it all over then find the first then return it
    }

    // do i really need this?
    public static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder().id(rs.getLong("id")).name(rs.getString("name")).age(rs.getInt("age")).build();
        }
    }
}
