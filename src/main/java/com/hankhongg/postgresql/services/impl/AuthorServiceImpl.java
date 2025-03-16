package com.hankhongg.postgresql.services.impl;

import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import com.hankhongg.postgresql.repositories.AuthorRepository;
import com.hankhongg.postgresql.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
    @Override
    public List<AuthorEntity> findAll(){
        // change to list rather than iterable
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    @Override
    public List<AuthorEntity> deleteAllAuthors(){

        authorRepository.deleteAll();
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
