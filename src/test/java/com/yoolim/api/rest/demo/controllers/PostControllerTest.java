package com.yoolim.api.rest.demo.controllers;

import com.yoolim.api.rest.demo.dtos.PostDto;
import com.yoolim.api.rest.demo.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Test
    void list() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("first title")
                ));
    }

    @Test
    void create() throws Exception {
        String json = """
                {
                    "title" : "new title",
                    "content" : "new content"
                }
                """;

        int oldCount = postRepository.findAll().size();

        System.out.println("#1 " + postRepository.findAll().stream().map(post -> new PostDto(post).getTitle()).toList());

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        System.out.println("#2 " + postRepository.findAll().stream().map(post -> new PostDto(post).getTitle()).toList());

        int newCount = postRepository.findAll().size();

        assertThat(newCount).isEqualTo(oldCount + 1);
    }
}