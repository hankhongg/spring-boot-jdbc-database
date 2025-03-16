package com.hankhongg.postgresql.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.dto.AuthorDto;
import com.hankhongg.postgresql.domain.dto.BookDto;
import com.hankhongg.postgresql.domain.entities.AuthorEntity;
import com.hankhongg.postgresql.domain.entities.BookEntity;
import com.hankhongg.postgresql.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private BookService bookService;
    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @BeforeEach
    void setUp() {
        bookService.deleteAllBooks();  // Implement a method to clear all books in the database
    }


    @Test
    public void http201BookCreateTestStatus() throws Exception {
        BookDto bookDto = TestDataUtil.getDtoTestBook1(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" +  bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON).content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
    @Test
    public void http201BookCreateTest() throws Exception {
        BookDto bookDto = TestDataUtil.getDtoTestBook1(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" +  bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON).content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }
    @Test
    public void http200BookListAllTestStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }
    @Test
    public void http200BookListAllTest() throws Exception {
        BookEntity bookEntity = TestDataUtil.getEntityTestBook1(null);
        bookService.createAndUpdateBook(bookEntity, bookEntity.getIsbn());
        mockMvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("B01")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("A Man Called Ove")
        );
    }
    @Test
    public void http200BookFindOneTestStatus() throws Exception {
        BookEntity bookEntity = TestDataUtil.getEntityTestBook1(null);
        bookService.createAndUpdateBook(bookEntity, bookEntity.getIsbn());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void http404BookFindOneTestStatusNOTFOUND() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.status().isNotFound()
                );
    }
    @Test
    public void http200BookFindOneTest() throws Exception {
        BookEntity bookEntity = TestDataUtil.getEntityTestBook1(null);
        bookService.createAndUpdateBook(bookEntity, bookEntity.getIsbn());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn()).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle())
        );
    }
    @Test
    public void http201BookFullUpdateTestStatusCREATED() throws Exception {
        BookDto bookDto = TestDataUtil.getDtoTestBook1(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/101").contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
    @Test
    public void http200BookFullUpdateTestStatus() throws Exception {
        BookEntity bookEntity = TestDataUtil.getEntityTestBook1(null);
        bookService.createAndUpdateBook(bookEntity, bookEntity.getIsbn());

        BookDto bookDto = TestDataUtil.getDtoTestBook1(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void http200BookFullUpdateTest() throws Exception {
        BookDto bookDto = TestDataUtil.getDtoTestBook1(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        BookEntity bookEntity = TestDataUtil.getEntityTestBook1(null);
        bookService.createAndUpdateBook(bookEntity, bookEntity.getIsbn());
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor())
        );
    }
}
