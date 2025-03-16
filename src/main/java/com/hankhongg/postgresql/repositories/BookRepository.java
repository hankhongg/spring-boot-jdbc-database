package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>,
        PagingAndSortingRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn(String isbn);
    // if not recognize => add annotation @Query("//sql")
}
