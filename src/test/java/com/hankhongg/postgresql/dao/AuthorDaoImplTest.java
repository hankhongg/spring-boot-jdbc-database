package com.hankhongg.postgresql.dao;

import com.hankhongg.postgresql.dao.impl.AuthorDaoImpl;
import com.hankhongg.postgresql.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
    @Mock // tạo đối tượng giả
    private JdbcTemplate jdbcTemplate;
    @InjectMocks // chích đối tượng giả vào dữ liệu cần test
    private AuthorDaoImpl authorDaoUnderTest;

    // kiểm tra bằng cách tạo 1 author và test tính năng A  uthor.create(Author newAuthor) => sau đó dùng verify để kiểm tra
    // xem jdbcTemplate có thực sự gọi update() với đúng tham số không
    @Test
    public void testCreateAuthor() {
        Author author = Author.builder().age(20).id(1L).name("hankhongg").build();
        authorDaoUnderTest.create(author);
        // verify (kiếm tra) xem query sau có thực sự được thực hiện không7
        verify(jdbcTemplate).update(
                eq("insert into authors (id, name, age) values (?,?,?)"),
                        eq(1L),
                        eq("hankhongg"),
                        eq(20)
        );
    }
}
