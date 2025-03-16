package com.hankhongg.postgresql.services;

import com.hankhongg.postgresql.domain.dto.BookDto;
import com.hankhongg.postgresql.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookEntity createBook(BookEntity bookEntity, String isbn);
    public List<BookEntity> findAll();
    public Optional<BookEntity> find(String isbn);

}
