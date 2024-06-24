package com.yoolim.api.rest.demo.daos;

import com.yoolim.api.rest.demo.dtos.PostDto;
import com.yoolim.api.rest.demo.exceptions.PostNotFound;

import java.util.ArrayList;
import java.util.List;

public class PostListDAO implements PostDAO {
//    private List<PostDto> postDtos = List.of(
//            new PostDto("1", "first title", "first content"),
//            new PostDto("2", "2등", "2등이다!!")
//    );

    private final List<PostDto> postDtos;

    public PostListDAO() {
        this.postDtos = new ArrayList();

        this.postDtos.add(new PostDto("1", "first title", "first content"));
        this.postDtos.add(new PostDto("2", "2등", "2등이다!!"));
    }

    @Override
    public List<PostDto> findAll() {
        // 원래 내부에 있는 컬렉션을 바깥에 바로 주는게 좋지 않음(혹시라도 바깥에서 원본 postDtos를 얻어서 add, remove 같은걸 하면 내부에 있는 원본을 고쳐버리면서 캡슐화가 깨지므로)
        return new ArrayList<>(postDtos);
    }

    @Override
    public PostDto findPost(String id) {
        return postDtos.stream().filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PostNotFound());
    }

    @Override
    public void save(PostDto postDto) {
        this.postDtos.add(postDto);
    }

    @Override
    public void delete(PostDto post) {
        this.postDtos.remove(post);
    }
}
