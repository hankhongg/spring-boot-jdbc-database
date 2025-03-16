package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

    }
    @Test
    public void authorCreateHibernateTest() {
//        Author author = TestDataUtil.getTestAuthor1();
//        author.setId(null);
//        authorRepository.save(author);
//        Optional<Author> result = authorRepository.findById(author.getId());
//        assertThat(result.isPresent()).isTrue();
//        assertThat(author).isEqualTo(result);

        Author author = TestDataUtil.getTestAuthor1();
        author = authorRepository.save(author);
        Optional<Author> result = authorRepository.findById(author.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(author).isEqualTo(result.get());
    }
    @Test
    public void authorAllCanBeCreatedAndRecalledHibernateTest(){
        Author author1 = TestDataUtil.getTestAuthor1();
        Author author2 = TestDataUtil.getTestAuthor2();
        Author author3 = TestDataUtil.getTestAuthor3();

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        Iterable<Author> authors = authorRepository.findAll();
        assertThat(authors).hasSize(3).containsExactly(author1, author2, author3);
    }
    @Test
    public void authorCanBeUpdatedAndRecalledHibernateTest(){
        Author author1 = TestDataUtil.getTestAuthor1();
        authorRepository.save(author1);
        // change the name but only to the instance NOT the db
        author1.setName("haruka");
        // then update it based on the id
        authorRepository.save(author1);

        Optional<Author> result = authorRepository.findById(author1.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author1);
    }

    @Test
    public void authorCanBeDeletedAndRecalledHibernateTest(){
//        Author author1 = TestDataUtil.getTestAuthor1();
//        authorDaoUnderTest.create(author1);
//        authorDaoUnderTest.delete(author1.getId());
//        Optional<Author> result = authorDaoUnderTest.findOne(author1.getId());
//        assertThat(result).isEmpty();
        Author author1 = TestDataUtil.getTestAuthor1();
        authorRepository.save(author1);
        authorRepository.delete(author1);
        Optional<Author> result = authorRepository.findById(author1.getId());
        assertThat(result).isEmpty();
    }
}