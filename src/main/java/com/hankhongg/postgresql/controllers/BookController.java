package com.hankhongg.postgresql.controllers;

import com.hankhongg.postgresql.domain.dto.BookDto;
import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import com.hankhongg.postgresql.domain.entities.BookEntity;
import com.hankhongg.postgresql.mappers.Mapper;
import com.hankhongg.postgresql.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity SavedBookEntity = bookService.createBook(bookEntity, isbn);
        BookDto convertedIntoBookDto = bookMapper.mapTo(SavedBookEntity);
        return new ResponseEntity<>(convertedIntoBookDto, HttpStatus.CREATED);
    }

    @GetMapping(path="/books")
    public List<BookDto> listBooks() {
        List<BookEntity> bookEntityList = bookService.findAll();
        List<BookDto> bookDtoList = bookEntityList.stream().map(bookMapper::mapTo).collect(Collectors.toList());
        return bookDtoList;
    }

    @GetMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> find(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.find(isbn);
        return foundBook.map(
                bookEntity -> {
                    BookDto bookDto = bookMapper.mapTo(bookEntity);
                    return new ResponseEntity<>(bookDto, HttpStatus.OK);
                }
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
