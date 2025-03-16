package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthorRepositoryIntegrationTests {
    private AuthorRepository authorRepository;
    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Autowired
    private DataSource dataSource;
    @Test
    void checkDatabaseConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Database URL: " + metaData.getURL());
        }
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
}