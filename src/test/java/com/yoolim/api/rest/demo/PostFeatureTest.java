package com.yoolim.api.rest.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostFeatureTest {
    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("게시물 목록 확인")
    void getListTest() {
        // restTemplate 설명 : https://adjh54.tistory.com/234
        String url = "http://localhost:" + port + "/posts";
        String body = restTemplate.getForObject(url, String.class);

        assertThat(body).contains("first title");
    }
}
