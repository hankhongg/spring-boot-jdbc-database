package com.hankhongg.postgresql.dao.impl;

import com.hankhongg.postgresql.dao.AuthorDao;
import com.hankhongg.postgresql.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
