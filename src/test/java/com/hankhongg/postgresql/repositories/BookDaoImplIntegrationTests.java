package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.Author;
import com.hankhongg.postgresql.domain.Book;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookDaoImplIntegrationTests(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

    }

    @Test
    public void bookCreateHibernateTest(){
        // hibernate jpa one
        Author author = TestDataUtil.getTestAuthor1();
        authorRepository.save(author);
        Book book = TestDataUtil.getTestBook1(author);
        bookRepository.save(book);
        Optional<Book> result = bookRepository.findByIsbn(book.getIsbn());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(book);
    }
    @Test
    public void bookAllCanBeCreatedAndUpdated(){
        //get 3 author instances
        Author author1 = TestDataUtil.getTestAuthor1();
        Author author2 = TestDataUtil.getTestAuthor2();
        Author author3 = TestDataUtil.getTestAuthor3();

        //add to database 3 author instances
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        //create 3 book instances
        Book book1 = TestDataUtil.getTestBook1(author1);
        Book book2 = TestDataUtil.getTestBook2(author2);
        Book book3 = TestDataUtil.getTestBook3(author3);

        //set authorid for 3 books


        //add to database 3 book instances
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // test findAll
        Iterable<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(3).containsExactly(book1, book2, book3);
     }
    @Test
    public void bookCanBeUpdatedAndRecalledHibernateTest(){
        Author author1 = TestDataUtil.getTestAuthor1();
        authorRepository.save(author1);
        Book book1 = TestDataUtil.getTestBook1(author1);
        bookRepository.save(book1);
        book1.setTitle("new title");
        bookRepository.save(book1);
        Optional<Book> result = bookRepository.findByIsbn(book1.getIsbn());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(book1);
    }
    @Test
    public void bookCanBeDeletedAndRecalledHibernateTest(){
        Author author1 = TestDataUtil.getTestAuthor1();
        authorRepository.save(author1);
        Book book1 = TestDataUtil.getTestBook1(author1);
        bookRepository.save(book1);
        bookRepository.delete(book1);
        // try to delete
        bookRepository.delete(book1);
        // save result
        Optional<Book> result = bookRepository.findByIsbn(book1.getIsbn());
        assertThat(result).isEmpty();

    }
}
