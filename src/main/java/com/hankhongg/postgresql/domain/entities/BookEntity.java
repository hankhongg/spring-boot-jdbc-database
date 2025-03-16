package com.hankhongg.postgresql.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    private String isbn;

    private String title; // | cascade = CascadeType.ALL means all changes made to authors would eventually make to books

    @ManyToOne(cascade = CascadeType.ALL) // 1 book 1 author BUT n books can belong to only 1 author
    @JoinColumn(name = "author_id") // join the other column - which obviously is "author_id"
    private AuthorEntity authorEntity; //bc its Spring so u can do it :o
}
