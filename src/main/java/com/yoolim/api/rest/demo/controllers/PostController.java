package com.yoolim.api.rest.demo.controllers;

import com.fasterxml.jackson.core.JacksonException;
import com.yoolim.api.rest.demo.dtos.PostDto;
import com.yoolim.api.rest.demo.exceptions.PostNotFound;
import com.yoolim.api.rest.demo.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
@RestController
public class PostController {
    private final PostService postService;
    //    PostService postService = new PostService();
//    private ObjectMapper objectMapper;

//    public PostController(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }

    public PostController() {
        this.postService = new PostService();
    }

    @GetMapping
    public List<PostDto> list() {
        List<PostDto> posts = postService.getPostList();
        return posts;
    }

    @GetMapping("/{id}")
//    public String detail(
    public PostDto detail(
            @PathVariable("id") String id) throws JacksonException {
        PostDto postDto = postService.getPostId(id);
//        String dtoToJsonString = objectMapper.writeValueAsString(postDto);
//
//        return dtoToJsonString;
        return postDto;
    }

    public String addPost(
            @PathVariable("id") String id, @RequestBody String body
    ) {
        return "{\"action\": \"게시글 생성\"," +
                " \"id\": \"" + id + "\"," +
                " \"body\": " + body +
                "}";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto postDto) {

        PostDto created = postService.create(postDto);

        return created;
    }

//    @PatchMapping("/{id}")
//    public String editPost(
//            @PathVariable("id") String id, @RequestBody String body
//    ) throws JacksonException {
////        return "게시글 수정: " + body;
//        PostDto postDto = new PostDto("1", "제목", "내용입니다");
//        postDto.setContent("내용 수정");
//        String contentChangeJsonString = objectMapper.writeValueAsString(postDto);
//
//        return contentChangeJsonString;
//    }

    @PatchMapping("/{id}")
    public PostDto update(
            @PathVariable("id") String id, @RequestBody PostDto postDto
    ) throws JacksonException {
        PostDto dto = postService.update(id, postDto);
//        return "게시글 수정: " + body;
//        PostDto bodyToDto = objectMapper.readValue(body, PostDto.class);
//        PostDto postDto = new PostDto(id, bodyToDto.getTitle(), bodyToDto.getContent());
//        postDto.setContent("내용 수정");
////        String contentChangeJsonString = objectMapper.writeValueAsString(postDto);
////
////        return contentChangeJsonString;
//
//        System.out.println("body =====> " + objectMapper.readValue(body, PostDto.class).getTitle());
        return dto;
    }

    @DeleteMapping("/{id}")
    public PostDto delete(
            @PathVariable("id") String id
    ) {
//        https://dev-jwblog.tistory.com/72

        PostDto postDto = postService.deletePost(id);

        return postDto;
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.\n";
    }
}
