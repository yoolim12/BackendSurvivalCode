package com.yoolim.api.rest.demo.dtos;

import com.yoolim.api.rest.demo.domain.Post;

public class PostDto {
    private String id;
    private String title;
    private String content;

    public PostDto() {
    }

    public PostDto(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDto(Post post) {
        this(post.id().toString(), post.title(), post.content().toString());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //    @JsonProperty("getter에 JsonProperty 어노테이션에 이렇게 원하는 값을 주면, 기존의 key값을 항상 어노테이션에 지정된 값으로 key값을 overwrite한다")
    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
