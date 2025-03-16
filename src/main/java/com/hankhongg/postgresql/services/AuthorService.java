package com.hankhongg.postgresql.services;

import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {
    public AuthorEntity save(AuthorEntity authorEntity);
    public List<AuthorEntity> findAll();
    public List<AuthorEntity> deleteAllAuthors();
}
