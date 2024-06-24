package com.yoolim.api.rest.demo.domain;

public class Post {
    private PostId id;
    private String title;
    private MultilineText content;

    public Post(PostId id, String title, MultilineText content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(String title, MultilineText content) {
        this.id = PostId.generate(); // id 생성에 대한 책임 전가
        this.title = title;
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public MultilineText content() {
        return content;
    }

    public void update(String title, String content) {
        System.out.println("title=" + title + "   content=" + content);

        if (title != null) this.title = title;
        if (content != null) this.content = MultilineText.of(content);
    }
}
