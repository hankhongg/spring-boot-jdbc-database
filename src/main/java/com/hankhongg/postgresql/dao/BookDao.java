package com.hankhongg.postgresql.dao;

import com.hankhongg.postgresql.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);
    Optional<Book> findOne(String isbn);
}
