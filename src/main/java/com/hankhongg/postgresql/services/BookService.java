package com.hankhongg.postgresql.services;

import com.hankhongg.postgresql.domain.dto.BookDto;
import com.hankhongg.postgresql.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookEntity createAndUpdateBook(BookEntity bookEntity, String isbn);
    public List<BookEntity> findAll();
    public Optional<BookEntity> find(String isbn);
    public boolean isExists(String isbn);
    public List<BookEntity> deleteAllBooks();
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity);
}
