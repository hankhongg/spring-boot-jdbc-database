package com.hankhongg.postgresql.dao;

import com.hankhongg.postgresql.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> findOne(Long id);
    List<Author> findAll();

    void update(long id, Author author);
    void delete(long id);
}
