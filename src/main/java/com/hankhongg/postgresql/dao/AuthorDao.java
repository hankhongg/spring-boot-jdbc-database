package com.hankhongg.postgresql.dao;

import com.hankhongg.postgresql.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> findOne(Long id);
}
