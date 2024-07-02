package com.yoolim.api.rest.demo.services;

import com.yoolim.api.rest.demo.domain.MultilineText;
import com.yoolim.api.rest.demo.domain.Post;
import com.yoolim.api.rest.demo.dtos.PostDto;
import com.yoolim.api.rest.demo.repositories.PostRepository;

import java.util.List;

//@Service
public class PostService {
    // PostDTO 목록 관리 --> 기능을 위한 토대

    //    private final PostDAO postDAO;
    private final PostRepository postRepository;

    //    https://kim-jong-hyun.tistory.com/31 -> Arrays.asList()와 List.of()의 차이

    public PostService() {
        this.postRepository = new PostRepository();
//        this.postDAO = new PostListDAO();
//        this.postDAO = new PostMapDAO();
    }

    public List<PostDto> getPostList() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> new PostDto(post)).toList();
//        return postDAO.findAll();
    }

    public PostDto getPostId(String id) {
//        return postDAO.findPost(id);
        Post post = postRepository.findPost(id);
        return new PostDto(post);
    }

    public PostDto create(PostDto postDto) {
//        String id = UUID.randomUUID().toString().replace("-", "");
//        postDto.setId(id);
//
//        postDAO.save(postDto);
        Post post = new Post(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent())
        ); // Post 객체 생성에 대한 책임을 Post 클래스로 전가

        postRepository.save(post);

        return new PostDto(post);
    }

    public PostDto update(String id, PostDto postDto) {
//        PostDto posts = postDAO.findPost(id);
        Post post = postRepository.update(id, postDto);

        // 이 방법이 별로지만, 일단 한다.
//        posts.setTitle(postDto.getTitle());
//        posts.setContent(postDto.getContent());

        return new PostDto(post);
    }

    public PostDto deletePost(String id) {
//        List<PostDto> postList = new ArrayList<PostDto>(postDtos);
//        PostDto post = postDAO.findPost(id);
//        postDAO.delete(post);
        Post post = postRepository.delete(id);
        return new PostDto(post);
    }

//    private PostDto findPost(String id) { // Extract Method(https://refactoring.com/catalog/extractFunction.html)
//        return postDtos.stream().filter(post -> post.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new PostNotFound());
//    }
}
