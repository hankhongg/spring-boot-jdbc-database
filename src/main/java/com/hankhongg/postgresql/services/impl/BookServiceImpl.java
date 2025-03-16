package com.hankhongg.postgresql.services.impl;

import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import com.hankhongg.postgresql.domain.entities.BookEntity;
import com.hankhongg.postgresql.repositories.BookRepository;
import com.hankhongg.postgresql.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookEntity createAndUpdateBook(BookEntity bookEntity, String isbn) {
        bookEntity.setIsbn(isbn);
        return this.bookRepository.save(bookEntity);
    }
    @Override
    public List<BookEntity> findAll(){
        return StreamSupport.stream(bookRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
    @Override
    public Optional<BookEntity> find(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    @Override
    public boolean isExists(String isbn) {
        return bookRepository.findByIsbn(isbn).isPresent();
    }
    @Override
    public List<BookEntity> deleteAllBooks() {
        bookRepository.deleteAll();
        return StreamSupport.stream(bookRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookRepository.findByIsbn(isbn).map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }

}
