package com.hankhongg.postgresql.dao.impl;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.dao.BookDao;
import com.hankhongg.postgresql.domain.Author;
import com.hankhongg.postgresql.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTests {
    private BookDaoImpl bookDaoUnderTest;
    @Autowired
    private AuthorDaoImpl authorDaoImpl;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl bookDaoUnderTest) {
        this.bookDaoUnderTest = bookDaoUnderTest;
    }
    @Test
    public void testBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.getTestAuthor();
        authorDaoImpl.create(author);
        Book book = TestDataUtil.getTestBook();
        book.setAuthorId(author.getId());
        bookDaoUnderTest.create(book);
        Optional<Book> result = bookDaoUnderTest.findOne(book.getIsbn());
        assertThat(result.isPresent());
        assertThat(result.get()).isEqualTo(book);
    }
}
