package com.hankhongg.postgresql.services;

import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public AuthorEntity save(AuthorEntity authorEntity);
    public List<AuthorEntity> findAll();
    public List<AuthorEntity> deleteAllAuthors();
    public Optional<AuthorEntity> find(Long id);
}
