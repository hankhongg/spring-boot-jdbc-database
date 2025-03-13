package com.hankhongg.postgresql;

import com.hankhongg.postgresql.domain.Author;
import com.hankhongg.postgresql.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {}

    // author related
    public static Author getTestAuthor1() {
        return Author.builder().age(20).id(1L).name("hankhongg").build();
    }
    public static Author getTestAuthor2() {
        return Author.builder().age(18).id(2L).name("teando").build();
    }
    public static Author getTestAuthor3() {
        return Author.builder().age(43).id(3L).name("huynhngoc").build();
    }

    // book related
    public static Book getTestBook1() {
        return Book.builder().isbn("B01").title("A Man Called Ove").authorId(1L).build();
    }
    public static Book getTestBook2() {
        return Book.builder().isbn("B02").title("Atomic Habits").authorId(2L).build();
    }
    public static Book getTestBook3() {
        return Book.builder().isbn("B03").title("Unreadable Book").authorId(3L).build();
    }
}
