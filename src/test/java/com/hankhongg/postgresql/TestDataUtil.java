package com.hankhongg.postgresql;

import com.hankhongg.postgresql.domain.Author;
import com.hankhongg.postgresql.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {}

    public static Author getTestAuthor() {
        return Author.builder().age(20).id(1L).name("hankhongg").build();
    }

    public static Book getTestBook() {
        return Book.builder().isbn("B01").title("A Man Called Ove").authorId(1L).build();
    }
}
