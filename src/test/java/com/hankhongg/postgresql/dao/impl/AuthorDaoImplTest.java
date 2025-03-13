package com.hankhongg.postgresql.dao.impl;

import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
    @Mock // tạo đối tượng giả
    private JdbcTemplate jdbcTemplate;
    @InjectMocks // chích đối tượng giả vào dữ liệu cần test
    private AuthorDaoImpl authorDaoUnderTest;

    // kiểm tra bằng cách tạo 1 author và test tính năng Author.create(Author newAuthor) => sau đó dùng verify để kiểm tra
    // xem jdbcTemplate có thực sự gọi update() với đúng tham số không
    @Test
    public void testCreateAuthor() {
        Author author = TestDataUtil.getTestAuthor();
        authorDaoUnderTest.create(author);
        // verify (kiếm tra) xem query sau có thực sự được thực hiện không7
        verify(jdbcTemplate).update(
                eq("insert into authors (id, name, age) values (?,?,?)"),
                        eq(1L),
                        eq("hankhongg"),
                        eq(20)
        );
    }

    @Test
    public void testFindOneAuthor() {
        authorDaoUnderTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("select id, name, age from authors where id = ? limit 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L));
    }
}
