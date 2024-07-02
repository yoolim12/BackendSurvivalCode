package com.yoolim.api.rest.demo.repositories;

import com.yoolim.api.rest.demo.domain.MultilineText;
import com.yoolim.api.rest.demo.domain.Post;
import com.yoolim.api.rest.demo.domain.PostId;
import com.yoolim.api.rest.demo.dtos.PostDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<PostId, Post> post;

    public PostRepository() {
        this.post = new HashMap<PostId, Post>();

        this.post.put(PostId.of("001"), new Post(PostId.of("001"), "first title", MultilineText.of("first content")));
        this.post.put(PostId.of("002"), new Post(PostId.of("002"), "2등", MultilineText.of("2등이다!!")));
    }

    public List<Post> findAll() {

//        return new ArrayList<>(this.post.values());
        return this.post.values().stream().sorted(
                        (a, b) -> {
                            System.out.println(a.title() + "          " + b.title());
                            return a.id().compareTo(b.id());
                        }
                )
                .toList();
    }

    public Post findPost(String id) {
        return post.get(PostId.of(id));
    }

    public void save(Post post) {
        this.post.put(post.id(), post);
    }

    public Post update(String id, PostDto postDto) {
        Post post = findPost(id);

        post.update(
                postDto.getTitle(),
                postDto.getContent()
        );

        return post;
    }

    public Post delete(String id) {
        Post post = findPost(id);
        this.post.remove(PostId.of(id));

        return post;
    }
}
