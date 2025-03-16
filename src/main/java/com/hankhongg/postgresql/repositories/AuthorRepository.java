package com.hankhongg.postgresql.repositories;

import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

}
