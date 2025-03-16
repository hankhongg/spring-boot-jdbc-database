package com.hankhongg.postgresql.services.impl;

import com.hankhongg.postgresql.domain.entities.BookEntity;
import com.hankhongg.postgresql.repositories.BookRepository;
import com.hankhongg.postgresql.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookEntity createBook(BookEntity bookEntity, String isbn) {
        bookEntity.setIsbn(isbn);
        return this.bookRepository.save(bookEntity);
    }
}
