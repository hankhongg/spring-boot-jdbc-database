package com.hankhongg.postgresql.controllers;

import com.hankhongg.postgresql.domain.dto.AuthorDto;
import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import com.hankhongg.postgresql.mappers.Mapper;
import com.hankhongg.postgresql.mappers.impl.AuthorMapper;
import com.hankhongg.postgresql.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;
    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    // but we cant just convert Author from presentation layer cuz
    // we dont want data leaks
    // => use Data Transfer Object
    @PostMapping(path="/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED); // this is AuthorDto then need to ResponeEntity<T>
    }
    @GetMapping(path="/authors")
    public List<AuthorDto> listAuthors(){
        List<AuthorEntity> authorEntityList = authorService.findAll();
        List<AuthorDto> gottenAuthorDtoList = authorEntityList.stream().map(authorMapper::mapTo).collect(Collectors.toList());
        return gottenAuthorDtoList;
    }
    @DeleteMapping(path="/authors")
    public List<AuthorDto> deleteAllAuthors() {
        List<AuthorEntity> authorEntityList = authorService.deleteAllAuthors();
        List<AuthorDto> authorDtoList = authorEntityList.stream().map(authorMapper::mapTo).collect(Collectors.toList());
        return authorDtoList;
    }
}
