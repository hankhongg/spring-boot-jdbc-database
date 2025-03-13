package com.hankhongg.postgresql.dao.impl;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.Author;
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
public class AuthorDaoImplIntegrationTests {
    private AuthorDaoImpl authorDaoUnderTest;
    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl authorDaoUnderTest) {
        this.authorDaoUnderTest = authorDaoUnderTest;
    }
    @Test
    public void testAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.getTestAuthor1(); // tạo test author
        authorDaoUnderTest.create(author); // add vào db
        Optional<Author> result = authorDaoUnderTest.findOne(author.getId()); // tìm author vừa đưa vào db
        assertThat(result).isPresent(); // KIỂM TRA XEM KẾT QUẢ CÓ Ở ĐÓ KO (KO ĐC RỖNG HOẶC NULL)
        assertThat(result.get()).isEqualTo(author); // KIỂM TRA XEM AUTHOR LẤY RA CÓ BẰNG AUTHOR ADD VÀO KO
    }
    @Test
    public void testAllAuthorsCanBeCreatedAndRecalled(){
        Author author1 = TestDataUtil.getTestAuthor1();
        Author author2 = TestDataUtil.getTestAuthor2();
        Author author3 = TestDataUtil.getTestAuthor3();

        authorDaoUnderTest.create(author1);
        authorDaoUnderTest.create(author2);
        authorDaoUnderTest.create(author3);

        List<Author> authors = authorDaoUnderTest.findAll();
        assertThat(authors).hasSize(3).containsExactly(author1, author2, author3);
    }

}
