package com.hankhongg.postgresql.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hankhongg.postgresql.TestDataUtil;
import com.hankhongg.postgresql.domain.dto.AuthorDto;
import com.hankhongg.postgresql.domain.entities.AuthorEntity;
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
public class AuthorControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, ObjectMapper mapper) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }
    @Test
    public void http201AuthorCreatedTest() throws Exception {
        // get test author data
        AuthorEntity authorEntity = TestDataUtil.getEntityTestAuthor1();
        // transfer into json to compare with mockmvc
        String authorJson = mapper.writeValueAsString(authorEntity);
        // check by mockmvn
        mockMvc.perform(MockMvcRequestBuilders.post("/authors").contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        // so it actually return http status of 200 instead of 201 => fix inside authorController
    }
    @Test
    public void http200AuthorReturnedSavedAuthorTest() throws Exception {
        AuthorDto authorDto = TestDataUtil.getDtoTestAuthor1();
        String authorJson = mapper.writeValueAsString(authorDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(
                        MediaType.APPLICATION_JSON
                )
                .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("hankhongg")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(20)
        );
    }
}
