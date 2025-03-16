package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityRepositoryIntegrationTests {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository authorRepository, BookRepository bookRepository) {
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

        AuthorEntity authorEntity = TestDataUtil.getEntityTestAuthor1();
        authorEntity = authorRepository.save(authorEntity);
        Optional<AuthorEntity> result = authorRepository.findById(authorEntity.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(authorEntity).isEqualTo(result.get());
    }
    @Test
    public void authorAllCanBeCreatedAndRecalledHibernateTest(){
        AuthorEntity authorEntity1 = TestDataUtil.getEntityTestAuthor1();
        AuthorEntity authorEntity2 = TestDataUtil.getEntityTestAuthor2();
        AuthorEntity authorEntity3 = TestDataUtil.getEntityTestAuthor3();

        authorRepository.save(authorEntity1);
        authorRepository.save(authorEntity2);
        authorRepository.save(authorEntity3);

        Iterable<AuthorEntity> authors = authorRepository.findAll();
        assertThat(authors).hasSize(3).containsExactly(authorEntity1, authorEntity2, authorEntity3);
    }
    @Test
    public void authorCanBeUpdatedAndRecalledHibernateTest(){
        AuthorEntity authorEntity1 = TestDataUtil.getEntityTestAuthor1();
        authorRepository.save(authorEntity1);
        // change the name but only to the instance NOT the db
        authorEntity1.setName("haruka");
        // then update it based on the id
        authorRepository.save(authorEntity1);

        Optional<AuthorEntity> result = authorRepository.findById(authorEntity1.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity1);
    }

    @Test
    public void authorCanBeDeletedAndRecalledHibernateTest(){
//        Author author1 = TestDataUtil.getTestAuthor1();
//        authorDaoUnderTest.create(author1);
//        authorDaoUnderTest.delete(author1.getId());
//        Optional<Author> result = authorDaoUnderTest.findOne(author1.getId());
//        assertThat(result).isEmpty();
        AuthorEntity authorEntity1 = TestDataUtil.getEntityTestAuthor1();
        authorRepository.save(authorEntity1);
        authorRepository.delete(authorEntity1);
        Optional<AuthorEntity> result = authorRepository.findById(authorEntity1.getId());
        assertThat(result).isEmpty();
    }
}