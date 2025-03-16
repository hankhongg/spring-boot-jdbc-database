package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import com.hankhongg.postgresql.domain.entities.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityDaoImplIntegrationTests {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookEntityDaoImplIntegrationTests(BookRepository bookRepository, AuthorRepository authorRepository) {
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
        AuthorEntity authorEntity = TestDataUtil.getTestAuthor1();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.getTestBook1(authorEntity);
        bookRepository.save(bookEntity);
        Optional<BookEntity> result = bookRepository.findByIsbn(bookEntity.getIsbn());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(bookEntity);
    }
    @Test
    public void bookAllCanBeCreatedAndUpdated(){
        //get 3 author instances
        AuthorEntity authorEntity1 = TestDataUtil.getTestAuthor1();
        AuthorEntity authorEntity2 = TestDataUtil.getTestAuthor2();
        AuthorEntity authorEntity3 = TestDataUtil.getTestAuthor3();

        //add to database 3 author instances
        authorRepository.save(authorEntity1);
        authorRepository.save(authorEntity2);
        authorRepository.save(authorEntity3);

        //create 3 book instances
        BookEntity bookEntity1 = TestDataUtil.getTestBook1(authorEntity1);
        BookEntity bookEntity2 = TestDataUtil.getTestBook2(authorEntity2);
        BookEntity bookEntity3 = TestDataUtil.getTestBook3(authorEntity3);

        //set authorid for 3 books


        //add to database 3 book instances
        bookRepository.save(bookEntity1);
        bookRepository.save(bookEntity2);
        bookRepository.save(bookEntity3);

        // test findAll
        Iterable<BookEntity> books = bookRepository.findAll();
        assertThat(books).hasSize(3).containsExactly(bookEntity1, bookEntity2, bookEntity3);
     }
    @Test
    public void bookCanBeUpdatedAndRecalledHibernateTest(){
        AuthorEntity authorEntity1 = TestDataUtil.getTestAuthor1();
        authorRepository.save(authorEntity1);
        BookEntity bookEntity1 = TestDataUtil.getTestBook1(authorEntity1);
        bookRepository.save(bookEntity1);
        bookEntity1.setTitle("new title");
        bookRepository.save(bookEntity1);
        Optional<BookEntity> result = bookRepository.findByIsbn(bookEntity1.getIsbn());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(bookEntity1);
    }
    @Test
    public void bookCanBeDeletedAndRecalledHibernateTest(){
        AuthorEntity authorEntity1 = TestDataUtil.getTestAuthor1();
        authorRepository.save(authorEntity1);
        BookEntity bookEntity1 = TestDataUtil.getTestBook1(authorEntity1);
        bookRepository.save(bookEntity1);
        bookRepository.delete(bookEntity1);
        // try to delete
        bookRepository.delete(bookEntity1);
        // save result
        Optional<BookEntity> result = bookRepository.findByIsbn(bookEntity1.getIsbn());
        assertThat(result).isEmpty();

    }
}
