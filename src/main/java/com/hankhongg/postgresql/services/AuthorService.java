package com.hankhongg.postgresql.services;

import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import org.springframework.stereotype.Service;

public interface AuthorService {
    public AuthorEntity save(AuthorEntity authorEntity);
}
