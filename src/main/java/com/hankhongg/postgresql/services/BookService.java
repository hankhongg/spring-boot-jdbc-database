package com.hankhongg.postgresql.services;

import com.hankhongg.postgresql.domain.dto.BookDto;
import com.hankhongg.postgresql.domain.entities.BookEntity;

public interface BookService {
    public BookEntity createBook(BookEntity bookEntity, String isbn);

}
