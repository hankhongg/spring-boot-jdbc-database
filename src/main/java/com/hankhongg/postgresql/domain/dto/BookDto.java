package com.hankhongg.postgresql.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String isbn;

    private String title; // | cascade = CascadeType.ALL means all changes made to authors would eventually make to books

    private AuthorDto authorEntity;
}
