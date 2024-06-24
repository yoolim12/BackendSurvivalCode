package com.yoolim.api.rest.demo.daos;

import com.yoolim.api.rest.demo.dtos.PostDto;
import com.yoolim.api.rest.demo.exceptions.PostNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostMapDAO implements PostDAO {
    private Map<String, PostDto> postDtos;

    public PostMapDAO() {
        this.postDtos = new HashMap();
        this.postDtos.put("1", new PostDto("1", "first title", "first content"));
        this.postDtos.put("2", new PostDto("2", "2등", "2등이다!!"));
    }

    @Override
    public List<PostDto> findAll() {
        return new ArrayList<>(postDtos.values());
//        return Arrays.asList(postDtos.values()); // 이건 왜 안되지?
    }

    @Override
    public PostDto findPost(String id) {
        PostDto postDto = postDtos.get(id);
        if (postDto == null) {
            throw new PostNotFound();
        }
        return postDto;
    }

    @Override
    public void save(PostDto postDto) {
        this.postDtos.put(postDto.getId(), postDto);
    }

    @Override
    public void delete(PostDto post) {
        postDtos.remove(post.getId());
    }
}
