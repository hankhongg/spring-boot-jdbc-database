package com.hankhongg.postgresql.dao.impl;

import com.hankhongg.postgresql.domain.Author;
import com.hankhongg.postgresql.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {
    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    BookDaoImpl bookDaoUnderTest;
    @Test
    public void testCreateBook(){
        Book book = Book.builder().isbn("B01").title("A Man Called Ove").authorId(1L).build();
        bookDaoUnderTest.create(book);
        verify(jdbcTemplate).update(eq("insert into books values (?,?,?)"),
                eq("B01"),
                eq("A Man Called Ove"),
                eq(1L));

    }
    @Test
    public void testFindOneBook(){
        bookDaoUnderTest.findOne("B01");
        verify(jdbcTemplate).query(eq("select isbn, title, authorId from books where isbn = ? limit 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("B01")
        );

    }
}
