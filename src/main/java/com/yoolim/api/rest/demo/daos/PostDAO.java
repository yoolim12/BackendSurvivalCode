package com.yoolim.api.rest.demo.daos;

import com.yoolim.api.rest.demo.dtos.PostDto;

import java.util.List;

// 명세
public interface PostDAO {
    List<PostDto> findAll();

    PostDto findPost(String id);

    void save(PostDto postDto);

    void delete(PostDto post);
}