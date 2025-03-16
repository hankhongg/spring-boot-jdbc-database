package com.hankhongg.postgresql;

import com.hankhongg.postgresql.domain.dto.AuthorDto;
import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import com.hankhongg.postgresql.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil() {}

    // author related
        // entities
    public static AuthorEntity getEntityTestAuthor1() {
        return AuthorEntity.builder().age(20).id(null).name("hankhongg").build();
    }
    public static AuthorEntity getEntityTestAuthor2() {
        return AuthorEntity.builder().age(18).id(null).name("teando").build();
    }
    public static AuthorEntity getEntityTestAuthor3() {
        return AuthorEntity.builder().age(43).id(null).name("huynhngoc").build();
    }
        // dtos
    public static AuthorDto getDtoTestAuthor1() {
            return AuthorDto.builder().age(20).id(null).name("hankhongg").build();
        }
    public static AuthorDto getDtoTestAuthor2() {
        return AuthorDto.builder().age(18).id(null).name("teando").build();
    }
    public static AuthorDto getDtoTestAuthor3() {
        return AuthorDto.builder().age(43).id(null).name("huynhngoc").build();
    }

    // book related
    public static BookEntity getTestBook1(AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("B01").title("A Man Called Ove").authorEntity(authorEntity).build();
    }
    public static BookEntity getTestBook2(AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("B02").title("Atomic Habits").authorEntity(authorEntity).build();
    }
    public static BookEntity getTestBook3(AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("B03").title("Unreadable Book").authorEntity(authorEntity).build();
    }
}
