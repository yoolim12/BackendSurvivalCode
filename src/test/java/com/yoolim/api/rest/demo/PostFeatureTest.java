package com.yoolim.api.rest.demo;

import com.yoolim.api.rest.demo.dtos.PostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostFeatureTest {
    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("게시물을 추가하고 목록에서 확인되는지 테스트")
    void getListTest() {
        // restTemplate 설명 : https://adjh54.tistory.com/234
        String url = "http://localhost:" + port + "/posts";
        PostDto postDto = new PostDto("new title", "new content");
        System.out.println("postDto =============> " + postDto.getContent());

        URI uri = restTemplate.postForLocation(url, postDto); // postForLocation : url 실행한 내용을 postDto 안에 넣겠다?(POST 요청을 보내고 헤더에 저장된 URI를 결과로 반환받는다 )
        System.out.println("uri ==============> " + uri);

        PostDto postDto2 = new PostDto("2nd title", "2nd content");
        restTemplate.postForLocation(url, postDto2);

        String body = restTemplate.getForObject(url, String.class); // getForObject : 주어진 URL 주소로 HTTP GET 메서드로 객체로 결과를 반환받는다
        System.out.println("body ===============> " + body);

        //        assertThat(body).contains("first title");
        assertThat(body).contains("new title");
        assertThat(body).contains("new content");
    }
}
