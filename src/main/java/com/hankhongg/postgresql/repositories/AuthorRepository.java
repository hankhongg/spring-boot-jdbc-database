package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
